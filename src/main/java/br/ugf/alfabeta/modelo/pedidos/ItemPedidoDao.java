/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

import br.ugf.alfabeta.modelo.entidades.Dao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.livros.Livro;
import java.util.List;

/**
 *
 * @author flavio
 */
public interface ItemPedidoDao extends Dao<ItemPedido> {
    
    public List<ItemPedido> listarPorLivro(Livro livro) throws ExcecaoDao;
    public List<ItemPedido> listarPorPedido(Pedido pedido) throws ExcecaoDao;
    public List<ItemPedido> listarPendentesAtivos() throws ExcecaoDao;
}
