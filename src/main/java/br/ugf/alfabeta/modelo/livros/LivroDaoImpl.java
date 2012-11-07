/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.livros;

import br.ugf.alfabeta.modelo.entidades.JpaDao;

/**
 *
 * @author ubuntu
 */
public class LivroDaoImpl extends JpaDao<Livro> implements LivroDao {
    
    public LivroDaoImpl() {
        super(Livro.class);
    }
}
