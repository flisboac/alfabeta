/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.clientes.ClienteDlo;
import br.ugf.alfabeta.modelo.clientes.ClienteDloImpl;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.web.entidades.CadastroBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ana
 */
@ManagedBean
@ViewScoped
public class CadastroClienteBean extends CadastroBean<Cliente> {
    
    private transient ClienteDlo clienteDlo = new ClienteDloImpl();
    
    private Cliente cliente;
    
    @Override
    @PostConstruct
    public void inicializar() {
        
        super.inicializar();
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public String cadastrarCliente(){
        
        String outcome = "";
        
        try {
            this.clienteDlo.inserir(cliente);
            
        } catch (ExcecaoDlo ex) {
            
            this.helper.erro(ex.getMessage());
        }
        
        return outcome;
    }
    
    @Override public ClienteDlo getDlo() { return this.clienteDlo; }
    @Override public Cliente getEntidade() { return this.cliente; }
    @Override public void setEntidade(Cliente cliente) { this.cliente = cliente; }
}
