/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.livros.LivroDlo;
import br.ugf.alfabeta.modelo.livros.LivroDloImpl;

/**
 *
 * @author ubuntu
 */
public class PedidoDloImpl extends EntidadeDloPersistencia<Pedido> implements PedidoDlo {
    
    private LivroDlo livroDlo = new LivroDloImpl();
    
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
                
                Livro livro = itemPedido.getLivro();
                int quantidade = livroDlo.getQuantidadeEmEstoque(livro);
                livroDlo.setQuantidadeEmEstoque(livro, quantidade + itemPedido.getQuantidade());
            }
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
                itemPedido.setPendente(false);
            }
            
            livroDlo.setQuantidadeEmEstoque(livro, quantidade - itemPedido.getQuantidade());
        }
        
        inserir(pedido);
    }
}
