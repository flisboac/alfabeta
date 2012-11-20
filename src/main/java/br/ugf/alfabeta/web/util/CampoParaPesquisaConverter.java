/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named("campoParaPesquisaConverter")
@FacesConverter(forClass = CampoParaPesquisa.class)
public class CampoParaPesquisaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        
        return CampoParaPesquisa.buscarPorCampo(string);
    }
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        
        return ((CampoParaPesquisa) o).getNomeCampo();
    }
}
