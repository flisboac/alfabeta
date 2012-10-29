/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ana
 */
public class BeanHelper {
    
    
    public HttpSession getSessao() {
        
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        return sessao;
    }
    
    
}
