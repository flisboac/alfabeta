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
                livroDlo.atualizar(livro);
                livro.setQuantidade(livro.getQuantidade() + itemPedido.getQuantidade());
                livroDlo.alterar(livro);
            }
        }
    }
}
