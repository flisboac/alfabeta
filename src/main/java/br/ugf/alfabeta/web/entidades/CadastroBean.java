/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.entidades;

import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.entidades.Entidade;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.validacoes.Identidade;
import br.ugf.alfabeta.modelo.validacoes.Persistencia;
import br.ugf.alfabeta.web.util.Bean;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author flavio
 */
public abstract class CadastroBean<T extends Entidade> extends Bean implements Serializable {
    
    
    // [ AÇÕES ] ===============================================================
    
    
    @PostConstruct
    public void inicializar() {
        
        T entidade = instanciarEntidade();
        setEntidade(entidade);
    }
    
    public T instanciarEntidade() {
        Dlo<T> dlo = getDlo();
        T retorno = null;
        
        // Consideramos que sempre haverá um construtor padrão público.
        try {
            retorno = dlo.getClasseEntidade().newInstance();
            
        } catch (InstantiationException ex) {
            Logger.getLogger(CadastroBean.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CadastroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
    
    public void prepararExclusao() {
        
        // Nada
    }
    
    public void prepararEdicao() {
        
        T entidade = getEntidade();
        
        if (entidade == null) {
            entidade = instanciarEntidade();
            setEntidade(entidade);
            
        } else {
            entidade = (T) entidade.clone();
            setEntidade(entidade);
        }
    }
    
    public void prepararInsercao() {
        
        T entidade = instanciarEntidade();
        setEntidade(entidade);
    }
    
    
    // [ GETTERS / SETTERS ] ===================================================
    
    
    abstract public Dlo<T> getDlo();
    abstract public T getEntidade();
    abstract public void setEntidade(T entidade);
    
    public boolean isEntidadeValida(Class<?>... grupos) {
        T entidade = getEntidade();
        Dlo<T> dlo = getDlo();
        boolean retorno = false;
        
        if (dlo != null && entidade != null) {
            retorno = true;
            
            try {
                dlo.validar(entidade, grupos);

            } catch (ExcecaoDlo ex) {
                retorno = false;
            }
        }
        
        return retorno;
    }
    
    public boolean isEntidadeValida() {
        
        return isEntidadeValida(Identidade.class);
    }
    
    public boolean isEdicaoValida() {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        boolean retorno = isEntidadeValida(Persistencia.class) && !(/*facesContext.isPostback() || */facesContext.isValidationFailed());
        return retorno;
    }
    
}
