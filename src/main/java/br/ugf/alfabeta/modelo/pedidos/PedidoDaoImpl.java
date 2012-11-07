/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

import br.ugf.alfabeta.modelo.entidades.JpaDao;

/**
 *
 * @author ubuntu
 */
public class PedidoDaoImpl extends JpaDao<Pedido> implements PedidoDao {

    public PedidoDaoImpl() {
        super(Pedido.class);
    }
}
