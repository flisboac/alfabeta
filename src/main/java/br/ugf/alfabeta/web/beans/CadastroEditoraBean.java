/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.editoras.Editora;
import br.ugf.alfabeta.modelo.editoras.EditoraDlo;
import br.ugf.alfabeta.modelo.editoras.EditoraDloImpl;
import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
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
public class CadastroEditoraBean extends CadastroBean<Editora> {
    
    private transient EditoraDlo editoraDlo = new EditoraDloImpl();
    
    private Editora editora;
    private List<Editora> editoras;
    
    // [ ACTIONS ] =============================================================
    
    @Override
    public void inicializar() {
        super.inicializar();
        carregarEditoras();
    }
    
    public void excluir() {
        try {
            this.editoraDlo.excluir(editora);
            carregarEditoras();
            getHelper().ok("Editora excluída com sucesso.");
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao excluir editora.");
        }
    }
    
    public void salvar() {
        try {
            this.editoraDlo.persistir(editora);
            carregarEditoras();
            getHelper().ok("Editora cadastrada com sucesso.");
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao cadastrar editora.");
        }
    }
    
    public void carregarEditoras() {
        
        this.editora = instanciarEntidade();
        try {
            this.editoras = this.editoraDlo.listar();
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao listar editoras.");
        }
    }
    
    // [ GETTERS / SETTERS ] ===================================================

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public List<Editora> getEditoras() {
        return editoras;
    }
    
    // [ ABSTRACT ] ============================================================

    @Override
    public Dlo<Editora> getDlo() {
        return this.editoraDlo;
    }

    @Override
    public Editora getEntidade() {
        return getEditora();
    }

    @Override
    public void setEntidade(Editora editora) {
        setEditora(editora);
    }
    
    
}
