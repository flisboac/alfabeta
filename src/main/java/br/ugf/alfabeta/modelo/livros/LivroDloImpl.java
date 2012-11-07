/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.livros;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;

/**
 *
 * @author ubuntu
 */
public class LivroDloImpl extends EntidadeDloPersistencia<Livro> implements LivroDlo {
    
    public LivroDloImpl() {
        super(new LivroDaoImpl());
    }
    
    public LivroDloImpl(LivroDao dao) {
        super(dao);
    }
}
