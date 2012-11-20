/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.entidades.OrdemListagem;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.livros.LivroDlo;
import br.ugf.alfabeta.modelo.livros.LivroDloImpl;
import br.ugf.alfabeta.web.util.Bean;
import br.ugf.alfabeta.web.util.CampoParaPesquisa;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author flavio
 */
@ManagedBean
@ViewScoped
public class AcervoBean extends Bean {
    
    private LivroDlo livroDlo = new LivroDloImpl();
    
    private boolean resultadosInvertidosParaPesquisa;
    private List<CampoParaPesquisa> camposDisponiveisParaPesquisa;
    private String nomeLivroParaPesquisa;
    
    private List<Livro> livros;
    
    @Override
    public void inicializar() {
        super.inicializar();
        
        this.camposDisponiveisParaPesquisa = Arrays.asList(CampoParaPesquisa.values());
    }
    
    public void atualizarPesquisa() {
        
        // A pesquisa ocorre com os nomes das propriedades da entidade.
        String[] nomesParaPesquisa = new String[camposDisponiveisParaPesquisa.size()];
        for (int i = 0; i < nomesParaPesquisa.length; i++) {
            nomesParaPesquisa[i] = camposDisponiveisParaPesquisa.get(i).getNomeCampo();
        }
        
        // ...
        OrdemListagem ordem = OrdemListagem.Ascendente;
        if (this.resultadosInvertidosParaPesquisa) {
            ordem = OrdemListagem.Descendente;
        }
        
        try {
            this.livros = this.livroDlo.listarOrdenado(ordem, nomesParaPesquisa);
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao listar livros", "Por favor, tente novamente!");
            return;
        }
        
        if (nomeLivroParaPesquisa != null && !nomeLivroParaPesquisa.isEmpty()) {
            Iterator<Livro> iter = this.livros.iterator();
            
            while (iter.hasNext()) {
                Livro livro = iter.next();
                
                if (!livro.getNome().contains(nomeLivroParaPesquisa)) {
                    iter.remove();
                }
            }
        }
        
        //getHelper().ok("Listando livros...");
    }

    public List<CampoParaPesquisa> getCamposDisponiveisParaPesquisa() {
        return camposDisponiveisParaPesquisa;
    }

    public void setCamposDisponiveisParaPesquisa(List<CampoParaPesquisa> camposDisponiveisParaPesquisa) {
        this.camposDisponiveisParaPesquisa = camposDisponiveisParaPesquisa;
    }
    
    public boolean isResultadosInvertidosParaPesquisa() {
        return resultadosInvertidosParaPesquisa;
    }

    public void setResultadosInvertidosParaPesquisa(boolean resultadosInvertidosParaPesquisa) {
        this.resultadosInvertidosParaPesquisa = resultadosInvertidosParaPesquisa;
    }
    
    public String getNomeLivroParaPesquisa() {
        return nomeLivroParaPesquisa;
    }

    public void setNomeLivroParaPesquisa(String nomeLivroParaPesquisa) {
        this.nomeLivroParaPesquisa = nomeLivroParaPesquisa;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
}
