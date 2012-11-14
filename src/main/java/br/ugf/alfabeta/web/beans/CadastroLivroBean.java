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
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.livros.LivroDlo;
import br.ugf.alfabeta.modelo.livros.LivroDloImpl;
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
public class CadastroLivroBean extends CadastroBean<Livro> {
    
    private transient EditoraDlo editoraDlo = new EditoraDloImpl();
    private transient LivroDlo livroDlo = new LivroDloImpl();
    private List<Editora> editoras;
    private List<Livro> livros ;
    private Livro livro;

    @Override
    public void inicializar() {
        super.inicializar();
        carregarLivros();
        
        try {
            this.editoras = editoraDlo.listar();
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao listar editoras.");
        }
    }
    
    public void salvar() {
        
        try {
            this.livroDlo.persistir(livro);
            getHelper().ok("Livro cadastrado com sucesso.");
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao cadastrar livro.");
        }
    }
    
    public void excluir() {
        
        try {
            this.livroDlo.excluir(livro);
            getHelper().ok("Livro excluído com sucesso.");
            carregarLivros();
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao excluir livro.");
        }
    }
    
    public void carregarLivros() {
        
        this.livro = instanciarEntidade();
        
        try {
            this.livros = this.livroDlo.listar();
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao listar livros.");
        }
    }
    
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Editora> getEditoras() {
        return editoras;
    }
    
    @Override
    public Dlo<Livro> getDlo() {
        return livroDlo;
    }

    @Override
    public Livro getEntidade() {
        return getLivro();
    }

    @Override
    public void setEntidade(Livro entidade) {
        setLivro(livro);
    }
    
    
    
}
