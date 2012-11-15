/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.editoras;

import br.ugf.alfabeta.modelo.editoras.Editora;
import br.ugf.alfabeta.modelo.editoras.EditoraDlo;
import br.ugf.alfabeta.modelo.editoras.EditoraDloImpl;
import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.web.entidades.JsfConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author flavio
 */
@Named("editoraConverter")
@FacesConverter(forClass=Editora.class)
public class EditoraConverter extends JsfConverter<Editora> {
    
    private EditoraDlo editoraDlo = new EditoraDloImpl();
    
    @Override
    public Dlo<Editora> getDlo() {
        return editoraDlo;
    }
}
