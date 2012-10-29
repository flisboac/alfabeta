
package br.ugf.alfabeta.web.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {
    
    private boolean usuarioLogado = false;
    
    public String efetuarLogin() {
        
        this.usuarioLogado = true;
        return "/";
    }
    
    public String efetuarLogoff() {
        
        this.usuarioLogado = false;
        return "/cliente";
    }
    
    public boolean isUsuarioLogado() {
        
        return this.usuarioLogado;
    }
}

