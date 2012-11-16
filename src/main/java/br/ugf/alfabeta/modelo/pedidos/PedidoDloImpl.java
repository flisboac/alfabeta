/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;

/**
 *
 * @author ubuntu
 */
public class PedidoDloImpl extends EntidadeDloPersistencia<Pedido> implements PedidoDlo {
    
    public PedidoDloImpl() {
        super(new PedidoDaoImpl(), new ValidadorPedido());
    }
    
    public PedidoDloImpl(PedidoDao dao) {
        super(dao, new ValidadorPedido());
    }
}
