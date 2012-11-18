/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.util;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 *
 * @author flavio
 */
public abstract class Bean implements Serializable {
    
    protected transient BeanHelper helper = new BeanHelper();
    
    @PostConstruct
    public void inicializar() {
        
        HttpSession sessao = helper.getSessao();
        String mensagemOk = null;
        String mensagemErro = null;
        
        if (sessao != null) {
            mensagemErro = (String) sessao.getAttribute("alfabeta.mensagemErro");
            sessao.removeAttribute("alfabeta.mensagemErro");
            mensagemOk = (String) sessao.getAttribute("alfabeta.mensagemOk");
            sessao.removeAttribute("alfabeta.mensagemOk");
        }
        
        if (mensagemOk != null) {
            helper.ok(mensagemOk);
        }
        
        if (mensagemErro != null) {
            helper.erro(mensagemErro);
        }
    }
    
    public boolean isClienteLogado() {
        
        return this.helper.isClienteLogado();
    }
    
    public Cliente getClienteLogadoAtual() {
        
        return this.helper.getClienteLogado();
    }
    
    public boolean isFuncionarioLogado() {
        
        return this.helper.isFuncionarioLogado();
    }
    
    public Funcionario getFuncionarioLogadoAtual() {
        
        return this.helper.getFuncionarioLogado();
    }
    
    protected BeanHelper getHelper() {
        return this.helper;
    }
}
