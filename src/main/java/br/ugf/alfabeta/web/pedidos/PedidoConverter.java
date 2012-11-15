/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.pedidos;

import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.pedidos.Pedido;
import br.ugf.alfabeta.modelo.pedidos.PedidoDlo;
import br.ugf.alfabeta.modelo.pedidos.PedidoDloImpl;
import br.ugf.alfabeta.web.entidades.JsfConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author flavio
 */
@Named("pedidoConverter")
@FacesConverter(forClass=Pedido.class)
public class PedidoConverter extends JsfConverter<Pedido> {
    
    private PedidoDlo pedidoDlo = new PedidoDloImpl();
    
    @Override
    public Dlo<Pedido> getDlo() {
        return pedidoDlo;
    }
}
