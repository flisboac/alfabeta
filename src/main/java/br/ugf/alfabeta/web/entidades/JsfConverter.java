/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.entidades;

import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.entidades.Entidade;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Ana
 */
public abstract class JsfConverter<T extends Entidade> implements Converter {

    public abstract Dlo<T> getDlo();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        Object retorno = null;
        Dlo<T> dlo = getDlo();
        
        if (value != null && value.matches("[0-9]+")) {
            
            long id = Long.getLong(value);
            try {
                retorno = dlo.obter(id);
            } catch (ExcecaoDlo ex) {
                Logger.getLogger(JsfConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        String retorno = null;
        Dlo<T> dlo = getDlo();
        
        if (value != null) {
            
            T entidade = (T) value;
            retorno = String.valueOf(entidade.getId());
        }
        
        return retorno;
    }
}
