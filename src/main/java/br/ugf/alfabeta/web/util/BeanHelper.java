/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.util;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ana
 */
public class BeanHelper implements Serializable {
    
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
        
        if (cliente != null) {
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
        
        if (funcionario != null) {
            getSessao().setAttribute("funcionario", funcionario);
        }
    }
    
    public void efetuarLogoffFuncionario() {
        
        getSessao().setAttribute("funcionario", null);
    }
    
    public boolean isFuncionarioLogado() {
        
        return getFuncionarioLogado() != null;
    }
    
    public void mensagem(Severity severidade, String idComponente, String titulo, String mensagem) {
        
        FacesMessage message = new FacesMessage(severidade, mensagem, titulo);
        FacesContext.getCurrentInstance().addMessage(idComponente, message);
    }
    
    public void ok(String idComponente, String titulo, String mensagem) {
        
        mensagem(FacesMessage.SEVERITY_INFO, idComponente, titulo, mensagem);
    }
    
    public void ok(String titulo, String mensagem) {
        
        mensagem(FacesMessage.SEVERITY_INFO, null, titulo, mensagem);
    }
    
    public void ok(String mensagem) {
        
        mensagem(FacesMessage.SEVERITY_INFO, null, "OK!", mensagem);
    }
    
    public void warn(String idComponente, String titulo, String mensagem) {
        
        mensagem(FacesMessage.SEVERITY_WARN, idComponente, titulo, mensagem);
    }
    
    public void warn(String titulo, String mensagem) {
        
        mensagem(FacesMessage.SEVERITY_WARN, null, titulo, mensagem);
    }
    
    public void warn(String mensagem) {
        
        mensagem(FacesMessage.SEVERITY_WARN, null, "OK!", mensagem);
    }
    
    public void erro(String idComponente, String titulo, String mensagem) {
        
        mensagem(FacesMessage.SEVERITY_ERROR, idComponente, titulo, mensagem);
    }
    
    public void erro(String titulo, String mensagem) {
        
        mensagem(FacesMessage.SEVERITY_ERROR, null, titulo, mensagem);
    }
    
    public void erro(String mensagem) {
        
        mensagem(FacesMessage.SEVERITY_ERROR, null, "OK!", mensagem);
    }
}
