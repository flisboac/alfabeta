
package br.ugf.alfabeta.web.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ClienteBean {
    
    private boolean usuarioLogado = false;
    
    public String efetuarLogin() {
        
        this.usuarioLogado = true;
        return "/index.xhtml";
    }
    
    public String efetuarLogoff() {
        
        this.usuarioLogado = false;
        return "/cliente/index";
    }
    
    public boolean isUsuarioLogado() {
        
        return this.usuarioLogado;
    }
}

