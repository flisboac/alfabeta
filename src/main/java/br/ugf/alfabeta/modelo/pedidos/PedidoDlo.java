/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

import br.ugf.alfabeta.modelo.entidades.DloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author ubuntu
 */
public interface PedidoDlo extends DloPersistencia<Pedido> {

    public void finalizarPedido(Pedido pedido) throws ExcecaoDlo;
    
}
