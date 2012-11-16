/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.entidades.Dao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.livros.Livro;
import java.util.List;

/**
 *
 * @author flavio
 */
public interface ItemEncomendaDao extends Dao<ItemEncomenda> {
    
    public List<ItemEncomenda> listarPorEncomenda(Encomenda encomenda) throws ExcecaoDao;
    public List<ItemEncomenda> listarPorLivro(Livro livro) throws ExcecaoDao;
}
