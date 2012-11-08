/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.web.util.BeanHelper;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ana
 */
@ManagedBean
@SessionScoped
public class CadastroClienteBean implements Serializable {
    
    private transient BeanHelper helper = new BeanHelper();
    private String nomecliente;
    private String emailCliente;
    private String senhaCliente;
    
    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }
    
    public String cadastrarCliente(){
        
        String outcome = "PortalCliente";
        return outcome;
    }
    
    public Cliente getClienteLogado() {
        
        return this.helper.getClienteLogado();
    }
    
    public boolean isClienteLogado() {
        
        return this.helper.isClienteLogado();
    }
}
