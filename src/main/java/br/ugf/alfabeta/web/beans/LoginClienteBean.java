/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.web.util.BeanHelper;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ubuntu
 */
@ManagedBean
@SessionScoped
public class LoginClienteBean {
    
    private transient BeanHelper helper = new BeanHelper();
    private String emailCliente;
    private String senhaCliente;

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
    
    public Cliente getClienteLogado() {
        
        return this.helper.getClienteLogado();
    }
    
    public String efetuarLogin() {
        
        String outcome = "PortalCliente";
        return outcome;
    }
    
    public String efetuarLogoff() {
        
        String outcome = "PortalCliente";
        return outcome;
    }
    
    public boolean isClienteLogado() {
        
        return this.helper.isClienteLogado();
    }
}
