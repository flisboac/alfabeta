/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.encomendas;

import br.ugf.alfabeta.modelo.encomendas.Encomenda;
import br.ugf.alfabeta.modelo.encomendas.EncomendaDlo;
import br.ugf.alfabeta.modelo.encomendas.EncomendaDloImpl;
import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.web.entidades.JsfConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author flavio
 */
@Named("encomendaConverter")
@FacesConverter(forClass=Encomenda.class)
public class EncomendaConverter extends JsfConverter<Encomenda> {
    
    private EncomendaDlo encomendaDlo = new EncomendaDloImpl();
    
    @Override
    public Dlo<Encomenda> getDlo() {
        return encomendaDlo;
    }
}
