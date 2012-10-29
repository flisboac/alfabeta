/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.util;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ana
 */
public class BeanHelper {
    
    
    public HttpSession getSessao() {
        
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        return sessao;
    }
    
    public Cliente getClienteLogado() {
        
        Cliente retorno = (Cliente) getSessao().getAttribute("cliente");
        return retorno;
    }
    
    public void efetuarLoginCliente(Cliente cliente) {
        
        if (cliente != null && cliente.isIdentidadeValida()) {
            getSessao().setAttribute("cliente", cliente);
        }
    }
    
    public void efetuarLogoffCliente() {
        
        getSessao().setAttribute("cliente", null);
    }
    
    public boolean isClienteLogado() {
        
        return getClienteLogado() != null;
    }
    
    public Funcionario getFuncionarioLogado() {
        
        Funcionario retorno = (Funcionario) getSessao().getAttribute("funcionario");
        return retorno;
    }
    
    public void efetuarLoginFuncionario(Funcionario funcionario) {
        
        if (funcionario != null && funcionario.isIdentidadeValida()) {
            getSessao().setAttribute("funcionario", funcionario);
        }
    }
    
    public void efetuarLogoffFuncionario() {
        
        getSessao().setAttribute("funcionario", null);
    }
    
    public boolean isFuncionarioLogado() {
        
        return getFuncionarioLogado() != null;
    }
}
