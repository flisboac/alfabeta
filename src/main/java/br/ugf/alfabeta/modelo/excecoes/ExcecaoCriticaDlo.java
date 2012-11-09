/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.excecoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;

/**
 *
 * @author flavio
 */
public class ExcecaoCriticaDlo extends ExcecaoDlo {
    
    private Set<ConstraintViolation<?>> violacoes;
    
    public ExcecaoCriticaDlo() {}
    public ExcecaoCriticaDlo(String msg) { super(msg); }
    public ExcecaoCriticaDlo(Throwable t) { super(t); }
    public ExcecaoCriticaDlo(String msg, Throwable t) { super(msg, t); }
    public ExcecaoCriticaDlo(Set<ConstraintViolation<?>> violacoes) { super();  this.violacoes = violacoes; }
    public ExcecaoCriticaDlo(Set<ConstraintViolation<?>> violacoes, String msg) { super(msg); this.violacoes = violacoes; }
    public ExcecaoCriticaDlo(Set<ConstraintViolation<?>> violacoes, Throwable t) { super(t); this.violacoes = violacoes; }
    public ExcecaoCriticaDlo(Set<ConstraintViolation<?>> violacoes, String msg, Throwable t) { super(msg, t); this.violacoes = violacoes; }

    public Set<ConstraintViolation<?>> getViolacoes() {
        return violacoes;
    }
    
    public boolean isMultiplo() {
        
        return violacoes != null && violacoes.size() > 1;
    }
    
    public List<String> getMensagensViolacao() {
        
        List<String> erros = new ArrayList<String>();
        
        for (ConstraintViolation<?> violacao : violacoes) {
            erros.add(violacao.getMessage());
        }
        
        return erros;
    }
    
}
