/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.encomendas.Encomenda;
import br.ugf.alfabeta.modelo.encomendas.EncomendaDlo;
import br.ugf.alfabeta.modelo.encomendas.EncomendaDloImpl;
import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.web.entidades.CadastroBean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ana
 */
@ManagedBean
@ViewScoped
public class CadastroEncomendaBean extends CadastroBean<Encomenda>{
    private transient EncomendaDlo encomendaDlo = new EncomendaDloImpl();
        private Encomenda encomenda;
        private List<Encomenda> encomendas;
    
    // [ ACTIONS ] =============================================================
        
    public void excuir(){
        
    }

    public void salvar(){
        
    }

    // [ GETTERS / SETTERS ] ===================================================
    
    public Encomenda getEncomenda() {
        return encomenda;
    }
    
    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }
    
    public List<Encomenda> getEncomendas(){
        return encomendas;
    }
    
    // [ ABSTRACT ] ============================================================

    @Override
    public Dlo<Encomenda> getDlo(){
        return this.encomendaDlo;
    }

    @Override
    public Encomenda getEntidade() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntidade(Encomenda entidade) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
        
}
