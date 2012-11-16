/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.clientes.ClienteDlo;
import br.ugf.alfabeta.modelo.clientes.ClienteDloImpl;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
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
    
    // Login
    private Cliente cliente = new Cliente();
    
    // Carrinho
    private List<Livro> livros;
    private Pedido pedido;
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public String efetuarLogin() {
        
        Cliente clienteReal = null;
        String mensagem = null;
        String outcome = "";
        
        try {
            clienteReal = this.clienteDlo.obterPorEmail(this.cliente.getEmail());    
        } catch (ExcecaoDlo ex) {}
        
        if (clienteReal == null) {
            mensagem = "E-mail ou senha inválidos.";
            
        } else {
            String senhaReal = clienteReal.getSenha();
            String senha = cliente.getSenha();
            
            if ((senhaReal == null && senha == null) ||
                    senhaReal != null && senhaReal.equals(senha)) {
                
                getHelper().efetuarLoginCliente(clienteReal);
                
            } else {
                mensagem = "E-mail ou senha inválidos.";
            }
        }
        
        if (mensagem != null) {
            getHelper().erro(mensagem);
            
        } else {
            getHelper().ok("Bem-vindo, " + clienteReal.getNome());
            outcome = "/cliente/index.xhtml";
        }
        
        return outcome;
    }
    
    public String efetuarLogoff() {
        
        String outcome = "";
        
        if (getHelper().isClienteLogado()) {
            getHelper().efetuarLogoffCliente();
            outcome = "/cliente/index.xhtml";
            getHelper().ok("Logoff efetuado com sucesso.");
        }
        
        return outcome;
    }
}
