
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.clientes.ClienteDlo;
import br.ugf.alfabeta.modelo.clientes.ClienteDloImpl;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.pedidos.EstadoPedido;
import br.ugf.alfabeta.modelo.pedidos.ItemPedido;
import br.ugf.alfabeta.modelo.pedidos.ItemPedidoDlo;
import br.ugf.alfabeta.modelo.pedidos.ItemPedidoDloImpl;
import br.ugf.alfabeta.modelo.pedidos.Pedido;
import br.ugf.alfabeta.modelo.pedidos.PedidoDlo;
import br.ugf.alfabeta.modelo.pedidos.PedidoDloImpl;
import br.ugf.alfabeta.web.util.Bean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author flavio
 */
@ManagedBean
@ViewScoped
public class PedidoBean extends Bean {
    
    private transient ItemPedidoDlo itemPedidoDlo = new ItemPedidoDloImpl();
    private transient PedidoDlo pedidoDlo = new PedidoDloImpl();
    private transient ClienteDlo clienteDlo = new ClienteDloImpl();
    
    
    private List<Pedido> pedidosEmAndamento;
    private List<Pedido> pedidosFinalizados;
    
    private Cliente clienteAtual;
    private Pedido pedido;
    
    
    // =========================================================================
    
    @Override
    public void inicializar() {
        super.inicializar();
        this.pedido = instanciarPedido();
        this.clienteAtual = getHelper().getClienteLogado();
        carregarPedidos();
    }
    
    
    public void carregarPedidos() {
        
        this.pedidosEmAndamento = new ArrayList<Pedido>();
        this.pedidosFinalizados = new ArrayList<Pedido>();
        
        try {
            clienteAtual = (Cliente) this.clienteDlo.obterCompleto(clienteAtual.getId());
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao obter lista de pedidos.");
            return;
        }
        
        List<Pedido> pedidos = clienteAtual.getPedidos();
        for (Pedido pedidoCliente : pedidos) {    
            try {
                List<ItemPedido> itensPedido = itemPedidoDlo.listarPorPedido(pedidoCliente);
                pedidoCliente.setItens(itensPedido);

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
                
            if (pedidoCliente.getEstado().isTerminal()) {
                this.pedidosFinalizados.add(pedidoCliente);
                
            } else {
                
                this.pedidosEmAndamento.add(pedidoCliente);
            }
        }
    }
    
    
    public Pedido instanciarPedido() {
        
        Pedido retorno = new Pedido();
        retorno.setItens(new ArrayList<ItemPedido>());
        return retorno;
    }
    
    
    public void cancelarPedido() {
        
        if (this.pedido == null) {
            getHelper().erro("Por favor, selecione um pedido.");
            return;
        }
        
        this.pedido.setEstado(EstadoPedido.Cancelado);
        this.pedido.setClienteCancelador(clienteAtual);
        this.pedido.setDataHoraCancelamento(new Date());
        
        try {
            this.pedidoDlo.finalizarPedido(pedido);
            carregarPedidos();
            getHelper().ok("Pedido finalizado com sucesso.");
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Não foi possível finalizar pedido.");
        }
    }
    
    // =========================================================================
    
    
    public List<Pedido> getPedidosEmAndamento() {
        return pedidosEmAndamento;
    }

    public void setPedidosEmAndamento(List<Pedido> pedidosEmAndamento) {
        this.pedidosEmAndamento = pedidosEmAndamento;
    }

    public List<Pedido> getPedidosFinalizados() {
        return pedidosFinalizados;
    }

    public void setPedidosFinalizados(List<Pedido> pedidosFinalizados) {
        this.pedidosFinalizados = pedidosFinalizados;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    // =========================================================================

    
}
