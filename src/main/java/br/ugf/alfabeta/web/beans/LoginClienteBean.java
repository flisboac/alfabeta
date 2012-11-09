/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.web.util.Bean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ubuntu
 */
@ManagedBean
@ViewScoped
public class LoginClienteBean extends Bean {
    
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
    
    public String efetuarLogin() {
        
        String outcome = "PortalCliente";
        return outcome;
    }
    
    public String efetuarLogoff() {
        
        String outcome = "PortalCliente";
        return outcome;
    }
}
