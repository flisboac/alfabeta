/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import br.ugf.alfabeta.modelo.funcionarios.FuncionarioDlo;
import br.ugf.alfabeta.modelo.funcionarios.FuncionarioDloImpl;
import br.ugf.alfabeta.web.entidades.CadastroBean;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    
    @Override
    public void inicializar(){
        super.inicializar();
        carregarFuncionarios();
     }
    
    
    public void salvar() {
        try {
            this.funcionarioDlo.persistir(funcionario);
            getHelper().ok("Funcionário Cadastrado com Sucesso!");
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao cadastrar o Funcionário!");
        }
                
    }
    
    public void excluir() {
        try {
            this.funcionarioDlo.excluir(funcionario);
            getHelper().ok("Funcionário Excluido com Sucesso!");
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao excluir o Funcionário!");
        }
    }
    
    public void carregarFuncionarios() {
        
        this.funcionario = instanciarEntidade();
        
        try {
            this.funcionarios = this.funcionarioDlo.listar();
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao listar livros.");
        }
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
