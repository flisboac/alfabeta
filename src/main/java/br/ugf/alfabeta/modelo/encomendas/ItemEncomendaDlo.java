/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.entidades.DloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.livros.Livro;
import java.util.List;

/**
 *
 * @author flavio
 */
public interface ItemEncomendaDlo extends DloPersistencia<ItemEncomenda> {
    
    public List<ItemEncomenda> listarPorEncomenda(Encomenda encomenda) throws ExcecaoDlo;
    public List<ItemEncomenda> listarPorLivro(Livro livro) throws ExcecaoDlo;
}
