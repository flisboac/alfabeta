/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import br.ugf.alfabeta.modelo.funcionarios.FuncionarioDlo;
import br.ugf.alfabeta.modelo.funcionarios.FuncionarioDloImpl;
import br.ugf.alfabeta.web.entidades.CadastroBean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ana
 */
@ManagedBean
@ViewScoped
public class CadastroFuncionarioBean extends CadastroBean<Funcionario> {
    
    private FuncionarioDlo funcionarioDlo = new FuncionarioDloImpl();
    private Funcionario funcionario;
    private List<Funcionario> funcionarios;
    
    
    // [ ACTIONS ] =============================================================
    
    
    public void salvar() {
        
    }
    
    public void excluir() {
        
    }
    
    // [ GETTERS / SETTERS ] ===================================================
    
    
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    
    // [ OVERRIDES ] ===========================================================
    
    @Override
    public Dlo<Funcionario> getDlo() {
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
