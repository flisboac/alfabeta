/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.debitos.Debito;
import br.ugf.alfabeta.modelo.debitos.DebitoDlo;
import br.ugf.alfabeta.modelo.debitos.DebitoDloImpl;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.web.util.Bean;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author flavio
 */
@ManagedBean
@ViewScoped
public class DebitoBean extends Bean {
    
    private DebitoDlo debitoDlo = new DebitoDloImpl();
    
    private List<Debito> debitosEmAndamento;
    private List<Debito> debitosPagos;
    
    @Override
    public void inicializar() {
        
        super.inicializar();
        
        debitosEmAndamento = new ArrayList<Debito>();
        debitosPagos = new ArrayList<Debito>();
        List<Debito> debitos;
        
        try {
            debitos = debitoDlo.listarPorCliente(getClienteLogadoAtual());
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao listar débitos", "Por favor, tente acessar a página mais tarde.");
            return;
        }
        
        for (Debito debito : debitos) {
            if (debito.isPago()) {
                debitosPagos.add(debito);
                
            } else {
                debitosEmAndamento.add(debito);
            }
        }
        
    }

    public List<Debito> getDebitosEmAndamento() {
        return debitosEmAndamento;
    }

    public void setDebitosEmAndamento(List<Debito> debitosEmAndamento) {
        this.debitosEmAndamento = debitosEmAndamento;
    }

    public List<Debito> getDebitosPagos() {
        return debitosPagos;
    }

    public void setDebitosPagos(List<Debito> debitosPagos) {
        this.debitosPagos = debitosPagos;
    }
    
}
