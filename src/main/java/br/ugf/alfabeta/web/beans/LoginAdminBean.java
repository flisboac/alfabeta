/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import br.ugf.alfabeta.modelo.funcionarios.FuncionarioDlo;
import br.ugf.alfabeta.modelo.funcionarios.FuncionarioDloImpl;
import br.ugf.alfabeta.web.entidades.CadastroBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author flisboac
 */
@ManagedBean
@ViewScoped
public class LoginAdminBean extends CadastroBean<Funcionario> {

    private transient FuncionarioDlo funcionarioDlo = new FuncionarioDloImpl();
    
    private Funcionario funcionario;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public String efetuarLogin() {
        
        Funcionario funcionarioReal = null;
        String mensagem = null;
        String outcome = "";
        
        try {
            funcionarioReal = this.funcionarioDlo.obterPorMatricula(this.funcionario.getMatricula());    
        } catch (ExcecaoDlo ex) {}
        
        if (funcionarioReal == null) {
            mensagem = "E-mail ou senha inválidos.";
            
        } else {
            String senhaReal = funcionarioReal.getSenha();
            String senha = funcionario.getSenha();
            
            if ((senhaReal == null && senha == null) ||
                    senhaReal != null && senhaReal.equals(senha)) {
                
                getHelper().efetuarLoginCliente(funcionarioReal);
                
            } else {
                mensagem = "E-mail ou senha inválidos.";
            }
        }
        
        if (mensagem != null) {
            getHelper().erro(mensagem);
            
        } else {
            getHelper().ok("Bem-vindo, " + funcionarioReal.getNome());
            outcome = "/admin/index.xhtml";
        }
        
        return outcome;
    }
    
    public String efetuarLogoff() {
        
        String outcome = "";
        
        if (getHelper().isFuncionarioLogado()) {
            getHelper().efetuarLogoffFuncionario();
            outcome = "/admin/login.xhtml";
            getHelper().ok("Logoff efetuado com sucesso.");
        }
        
        return outcome;
    }
    
    @Override
    public Dlo getDlo() {
        return funcionarioDlo;
    }

    @Override
    public Funcionario getEntidade() {
        return getFuncionario();
    }

    @Override
    public void setEntidade(Funcionario funcionario) {
        setFuncionario(funcionario);
    }
    
    
}
