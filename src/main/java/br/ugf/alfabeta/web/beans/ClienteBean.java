/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.ClienteDlo;
import br.ugf.alfabeta.modelo.clientes.ClienteDloImpl;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.livros.LivroDlo;
import br.ugf.alfabeta.modelo.livros.LivroDloImpl;
import br.ugf.alfabeta.modelo.pedidos.Pedido;
import br.ugf.alfabeta.modelo.pedidos.PedidoDlo;
import br.ugf.alfabeta.modelo.pedidos.PedidoDloImpl;
import br.ugf.alfabeta.web.util.Bean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ubuntu
 */
@ManagedBean
@SessionScoped
public class ClienteBean extends Bean {
    
    private transient ClienteDlo clienteDlo = new ClienteDloImpl();
    private transient LivroDlo livroDlo = new LivroDloImpl();
    private transient PedidoDlo pedidoDlo = new PedidoDloImpl();
    
    // Carrinho
    private List<Livro> livros;
    private Pedido pedido;
    
    public ClienteBean() {
        
    }
    
    public String getValorTotalFormatado() {
        
        return "R$ 0,00";
    }
    
    public void comprar() {
        
    }
    
    public void esvaziar() {
        
    }
}
