/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.livros;

import br.ugf.alfabeta.modelo.entidades.Dao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import java.util.List;

/**
 *
 * @author ubuntu
 */
public interface LivroDao extends Dao<Livro> {
    
    public List<Livro> listarEmFalta() throws ExcecaoDao;
}
