/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.pedidos.ItemPedido;
import br.ugf.alfabeta.modelo.pedidos.ItemPedidoDlo;
import br.ugf.alfabeta.modelo.pedidos.ItemPedidoDloImpl;
import br.ugf.alfabeta.modelo.pedidos.Pedido;
import br.ugf.alfabeta.modelo.pedidos.PedidoDlo;
import br.ugf.alfabeta.modelo.pedidos.PedidoDloImpl;
import br.ugf.alfabeta.web.util.Bean;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ana
 */
@ManagedBean
@ViewScoped
public class VisualizacaoPedidoBean extends Bean {

    private transient PedidoDlo pedidoDlo = new PedidoDloImpl();
    private transient ItemPedidoDlo itemPedidoDlo = new ItemPedidoDloImpl();
    private List<Pedido> pedidos;
    private Pedido pedido;

    @Override
    public void inicializar() {
        super.inicializar();

        try {
            this.pedidos = pedidoDlo.listar();

            for (Pedido pedido : pedidos) {
                try {
                    List<ItemPedido> itensPedido = itemPedidoDlo.listarPorPedido(pedido);
                    pedido.setItens(itensPedido);

                } catch (ExcecaoDlo ex) {
                    getHelper().getSessao().setAttribute("alfabeta.mensagemErro", "Erro ao listar pedidos!");

                    try {
                        String context = getHelper().getRequest().getContextPath();
                        getHelper().getResponse().sendRedirect(context + "/portal/index.xhtml");

                    } catch (IOException ex1) {
                        // Ferrou!
                        Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex1);
                    }

                    return;
                }
            }
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao listar pedidos!");
        }
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
