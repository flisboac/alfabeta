/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.util;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import java.io.Serializable;

/**
 *
 * @author flavio
 */
public class Bean implements Serializable {
    
    protected transient BeanHelper helper = new BeanHelper();
    
    public boolean isClienteLogado() {
        
        return this.helper.isClienteLogado();
    }
    
    public Cliente getClienteLogado() {
        
        return this.helper.getClienteLogado();
    }
    
    public boolean isFuncionarioLogado() {
        
        return this.helper.isFuncionarioLogado();
    }
    
    public Funcionario getFuncionarioLogado() {
        
        return this.helper.getFuncionarioLogado();
    }
    
    protected BeanHelper getHelper() {
        return this.helper;
    }
}
