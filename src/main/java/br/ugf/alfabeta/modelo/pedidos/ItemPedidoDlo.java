/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

import br.ugf.alfabeta.modelo.entidades.DloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.livros.Livro;
import java.util.List;

/**
 *
 * @author flavio
 */
public interface ItemPedidoDlo extends DloPersistencia<ItemPedido> {
    
    public List<ItemPedido> listarPorLivro(Livro livro) throws ExcecaoDlo;
    public List<ItemPedido> listarPorPedido(Pedido pedido) throws ExcecaoDlo;
    public List<ItemPedido> listarPendentesAtivos() throws ExcecaoDlo;
}
