/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.debitos;

import br.ugf.alfabeta.modelo.debitos.Debito;
import br.ugf.alfabeta.modelo.debitos.DebitoDlo;
import br.ugf.alfabeta.modelo.debitos.DebitoDloImpl;
import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.web.entidades.JsfConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author flavio
 */

@Named("debitoConverter")
@FacesConverter(forClass=Debito.class)
public class DebitoConverter extends JsfConverter<Debito> {
    
    private DebitoDlo debitoDlo = new DebitoDloImpl();
    
    @Override
    public Dlo<Debito> getDlo() {
        return debitoDlo;
    }
    
}
