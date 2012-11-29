/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

import br.ugf.alfabeta.modelo.debitos.Debito;
import br.ugf.alfabeta.modelo.debitos.DebitoDlo;
import br.ugf.alfabeta.modelo.debitos.DebitoDloImpl;
import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.livros.LivroDlo;
import br.ugf.alfabeta.modelo.livros.LivroDloImpl;
import java.math.BigDecimal;

/**
 *
 * @author ubuntu
 */
public class PedidoDloImpl extends EntidadeDloPersistencia<Pedido> implements PedidoDlo {
    
    private ItemPedidoDlo itemPedidoDlo = new ItemPedidoDloImpl();
    private LivroDlo livroDlo = new LivroDloImpl();
    private DebitoDlo debitoDlo = new DebitoDloImpl();
    
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
                
            } else {
                // Reduz o estoque do livro
                itemPedido.setPendente(false);
                livroDlo.setQuantidadeEmEstoque(livro, quantidade - itemPedido.getQuantidade());
            }
            
            itemPedidoDlo.inserir(itemPedido);
        }
        
    }
}
