
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.web.util.BeanHelper;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {
    
    private String loginCliente;
    private String senhaCliente;
    private transient Cliente clienteLogado = new Cliente();
    private transient BeanHelper helper = new BeanHelper();

    public String getLoginCliente() {
        return loginCliente;
    }

    public void setLoginCliente(String loginCliente) {
        this.loginCliente = loginCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }
    
    
    public String efetuarLogin() {
        String outcome = "";
        
        return outcome;
    }
    
    public String efetuarLogoff() {
        String outcome = "";
        
        return outcome;
    }
    
    public boolean isClienteLogado() {
        
        return this.helper.isClienteLogado();
    }
}

