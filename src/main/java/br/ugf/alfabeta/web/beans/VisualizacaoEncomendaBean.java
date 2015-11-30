/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.encomendas.Encomenda;
import br.ugf.alfabeta.modelo.encomendas.EncomendaDlo;
import br.ugf.alfabeta.modelo.encomendas.EncomendaDloImpl;
import br.ugf.alfabeta.modelo.encomendas.EstadoEncomenda;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.web.util.Bean;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author flavio
 */
@ManagedBean
@ViewScoped
public class VisualizacaoEncomendaBean extends Bean {
    
    private transient EncomendaDlo encomendaDlo = new EncomendaDloImpl();
    
    private List<EstadoEncomenda> estados = Arrays.asList(EstadoEncomenda.values());
    private List<Encomenda> encomendas;
    private Encomenda encomenda;

    // [ ACTIONS ] =============================================================
    
    @PostConstruct
    public void inicializar() {
        carregarEncomendas();
    }
    
    public void carregarEncomendas() {
        this.encomenda = new Encomenda();
        try {
            this.encomendas = this.encomendaDlo.listar();
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao listar encomendas.");
        }
    }
    
    public void prepararVerificacao() {
        
    }
    
    public void prepararVisualizacao() {
        
    }
    
    public void prepararFinalizacao() {
        
    }
    
    public void cadastrar() {
        
    }
    
    public void finalizar() {
        try {
            encomendaDlo.finalizar(encomenda);
            inicializar();
            getHelper().ok("Encomenda finalizada com sucesso!");
            
        } catch (ExcecaoDlo ex) {
            inicializar();
            getHelper().erro("Erro ao finalizar a encomenda!", ex.getLocalizedMessage());
        }
    }
    
    // [ GETTERS / SETTERS ] ===================================================
    
    public boolean isCadastroValido() {
        boolean retorno = false;
        return retorno;
    }
    
    public boolean isFinalizacaoValida() {
        boolean retorno = false;
        return retorno;
    }
    
    public List<EstadoEncomenda> getEstados() {
        return estados;
    }
    
    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }

    public List<Encomenda> getEncomendas() {
        return encomendas;
    }
    
}
