/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.livros;

import br.ugf.alfabeta.modelo.editoras.Editora;
import br.ugf.alfabeta.modelo.entidades.DloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import java.util.List;

/**
 *
 * @author ubuntu
 */
public interface LivroDlo extends DloPersistencia<Livro> {
    
    public List<Livro> listarPorEditora(Editora editora) throws ExcecaoDlo;
    public int getQuantidadeEmEstoque(Livro livro) throws ExcecaoDlo;
    public void setQuantidadeEmEstoque(Livro livro, int quantidade) throws ExcecaoDlo;
}
