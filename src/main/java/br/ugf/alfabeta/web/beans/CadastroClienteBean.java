/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.clientes.ClienteDlo;
import br.ugf.alfabeta.modelo.clientes.ClienteDloImpl;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.validacoes.Persistencia;
import br.ugf.alfabeta.web.util.BeanHelper;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ana
 */
@ManagedBean
@ViewScoped
public class CadastroClienteBean implements Serializable {
    
    private transient ClienteDlo clienteDlo = new ClienteDloImpl();
    
    private BeanHelper helper = new BeanHelper();
    private Cliente cliente;
    
    @PostConstruct
    public void inicializar() {
        
        this.cliente = new Cliente();
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public BeanHelper getHelper() {
        return helper;
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
    
}
