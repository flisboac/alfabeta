/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.encomendas;

import br.ugf.alfabeta.modelo.encomendas.ItemEncomenda;
import br.ugf.alfabeta.modelo.encomendas.ItemEncomendaDlo;
import br.ugf.alfabeta.modelo.encomendas.ItemEncomendaDloImpl;
import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.web.entidades.JsfConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author flavio
 */
@Named("itemEncomendaConverter")
@FacesConverter(forClass=ItemEncomenda.class)
public class ItemEncomendaConverter extends JsfConverter<ItemEncomenda> {
    
    private ItemEncomendaDlo itemEncomendaDlo = new ItemEncomendaDloImpl();

    @Override
    public Dlo<ItemEncomenda> getDlo() {
        return itemEncomendaDlo;
    }
}
