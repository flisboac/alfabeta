/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import br.ugf.alfabeta.modelo.funcionarios.FuncionarioDlo;
import br.ugf.alfabeta.modelo.funcionarios.FuncionarioDloImpl;
import br.ugf.alfabeta.web.util.Bean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author flisboac
 */
@ManagedBean
@ViewScoped
public class InstalacaoBean extends Bean {
    
    private transient FuncionarioDlo funcionarioDlo = new FuncionarioDloImpl();
    
    public void instalar() {
        
        Funcionario funcionario = new Funcionario();
        funcionario.setEmail("root@localhost");
        funcionario.setMatricula("root");
        funcionario.setNome("Super-usuário");
        funcionario.setSenha("root");
        
        boolean rootExiste;
        try {
            rootExiste = funcionarioDlo.existe(funcionario);
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao verificar a existência do super-usuário.");
            return;
        }
        
        if (!rootExiste) {
            try {
                funcionarioDlo.inserir(funcionario);
                
            } catch (ExcecaoDlo ex) {
                getHelper().erro("Erro ao inserir super-usuário.");
                return;
            }
        }
        
        getHelper().ok("Carga inicial inserida com sucesso.");
    }
    
    public void invalidarSessao() {
        
        HttpSession sessao = getHelper().getSessao(false);
        
        if (sessao != null) {
            sessao.invalidate();
            getHelper().ok("Sessão invalidada com sucesso.");
            
        } else {
            getHelper().ok("Não há sessão para invalidar.");
        }
    }
}
