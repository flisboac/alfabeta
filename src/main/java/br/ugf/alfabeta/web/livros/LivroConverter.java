/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.livros;

import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.livros.LivroDlo;
import br.ugf.alfabeta.modelo.livros.LivroDloImpl;
import br.ugf.alfabeta.web.entidades.JsfConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author flavio
 */
@Named("livroConverter")
@FacesConverter(forClass=Livro.class)
public class LivroConverter extends JsfConverter<Livro> {

    private LivroDlo livroDlo = new LivroDloImpl();
    
    @Override
    public Dlo<Livro> getDlo() {
        return livroDlo;
    }
}
