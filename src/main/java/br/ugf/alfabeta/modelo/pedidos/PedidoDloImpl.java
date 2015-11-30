/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

import br.ugf.alfabeta.modelo.debitos.Debito;
import br.ugf.alfabeta.modelo.debitos.DebitoDlo;
import br.ugf.alfabeta.modelo.debitos.DebitoDloImpl;
import br.ugf.alfabeta.modelo.editoras.Editora;
import br.ugf.alfabeta.modelo.encomendas.Encomenda;
import br.ugf.alfabeta.modelo.encomendas.EncomendaDlo;
import br.ugf.alfabeta.modelo.encomendas.EncomendaDloImpl;
import br.ugf.alfabeta.modelo.encomendas.EstadoEncomenda;
import br.ugf.alfabeta.modelo.encomendas.ItemEncomenda;
import br.ugf.alfabeta.modelo.encomendas.ItemEncomendaDlo;
import br.ugf.alfabeta.modelo.encomendas.ItemEncomendaDloImpl;
import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.livros.LivroDlo;
import br.ugf.alfabeta.modelo.livros.LivroDloImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ubuntu
 */
public class PedidoDloImpl extends EntidadeDloPersistencia<Pedido> implements PedidoDlo {
    
    private ItemPedidoDlo itemPedidoDlo = new ItemPedidoDloImpl();
    private LivroDlo livroDlo = new LivroDloImpl();
    private DebitoDlo debitoDlo = new DebitoDloImpl();
    private EncomendaDlo encomendaDlo = new EncomendaDloImpl();
    private ItemEncomendaDlo itemEncomendaDlo = new ItemEncomendaDloImpl();
    
    public PedidoDloImpl() {
        super(new PedidoDaoImpl(), new ValidadorPedido());
    }

    public PedidoDloImpl(PedidoDao dao) {
        super(dao, new ValidadorPedido());
    }
    
    @Override
    public void finalizarPedido(Pedido pedido) throws ExcecaoDlo {

        if (pedido == null) {
            throw new ExcecaoCriticaDlo("Pedido nulo passado para cancelamento.");
        }

        if (!pedido.getEstado().isTerminal()) {
            throw new ExcecaoCriticaDlo("Tentando finalizar pedido com estado não-terminal.");
        }

        pedido.setDataHoraCancelamento(new Date());
        alterar(pedido);

        // Repõe o estoque para pedidos cancelados.
        if (pedido.getEstado() == EstadoPedido.Cancelado) {
            for (ItemPedido itemPedido : pedido.getItens()) {
                
                // Apenas itens não pendentes devem ser repostos.
                if (!itemPedido.isPendente()) {
                    Livro livro = itemPedido.getLivro();
                    int quantidade = livroDlo.getQuantidadeEmEstoque(livro);
                    livroDlo.setQuantidadeEmEstoque(livro, quantidade + itemPedido.getQuantidade());
                }
            }
            
        // Pedido foi finalizado, gerar débitos
        } else if (pedido.getEstado() == EstadoPedido.Atendido) {
            
            BigDecimal valorTotal = pedido.getValorTotal();
            Debito debito = new Debito();
            debito.setValor(valorTotal);
            debito.setPedido(pedido);
            debitoDlo.inserir(debito);
        }
    }
    
    @Override
    public void efetuarPedido(Pedido pedido) throws ExcecaoDlo {
        
        if (pedido == null) {
            throw new ExcecaoCriticaDlo("Pedido nulo passado para cancelamento.");
        }

        if (pedido.getEstado() != EstadoPedido.Criado) {
            throw new ExcecaoCriticaDlo("Tentando efetuar pedido com estado não-inicial.");
        }
        
        if (pedido.getId() != null || existe(pedido)) {
            throw new ExcecaoCriticaDlo("Tentando efetuar pedido já existente.");
        }
        
        inserir(pedido);
        Map<Editora, List<ItemPedido>> itensPendentes = new HashMap<Editora, List<ItemPedido>>();
        
        // Verifica se existe quantidade suficiente para todos os livros
        for (ItemPedido itemPedido : pedido.getItens()) {
            
            Livro livro = itemPedido.getLivro();
            int quantidade = livroDlo.getQuantidadeEmEstoque(livro);
            
            if (quantidade < itemPedido.getQuantidade()) {
//                throw new ExcecaoCriticaDlo("Não é possível atender a quantidade de "
//                        + itemPedido.getQuantidade() + " livro(s)"
//                        + " (Cód.: " + livro.getCodigo()
//                        + ", estoque: " + quantidade + ").");
                itemPedido.setPendente(true);
                //itemPedido.setQuantidade(itemPedido.getQuantidade() - quantidade);
                //livroDlo.setQuantidadeEmEstoque(livro, 0);
                Editora editora = itemPedido.getLivro().getEditora();
                if (!itensPendentes.containsKey(editora)) {
                    itensPendentes.put(editora, new ArrayList<ItemPedido>());
                }
                itensPendentes.get(editora).add(itemPedido);
                
            } else {
                // Reduz o estoque do livro
                itemPedido.setPendente(false);
                livroDlo.setQuantidadeEmEstoque(livro, quantidade - itemPedido.getQuantidade());
            }
            
            itemPedidoDlo.inserir(itemPedido);
        }
        
        if (!itensPendentes.isEmpty()) {            
            for (Map.Entry<Editora, List<ItemPedido>> pendente : itensPendentes.entrySet()) {
                Editora editora = pendente.getKey();
                List<ItemPedido> itensPedido = pendente.getValue();
                
                Encomenda encomenda = new Encomenda();
                encomenda.setEditora(editora);
                encomenda.setCodigo(Encomenda.PREFIXO + String.format("%016X", new Date().getTime()));
                encomenda = encomendaDlo.persistir(encomenda);
                
                for (ItemPedido itemPedido : itensPedido) {
                    Livro livro = itemPedido.getLivro();
                    ItemEncomenda itemEncomenda = new ItemEncomenda();
                    itemEncomenda.setEncomenda(encomenda);
                    itemEncomenda.setLivro(itemPedido.getLivro());
                    itemEncomenda.setQuantidade(livro.getQuantidadeMinima() + (itemPedido.getQuantidade() - livroDlo.getQuantidadeEmEstoque(livro)));
                    itemEncomendaDlo.persistir(itemEncomenda);
                }
            }
        }
    }
}
