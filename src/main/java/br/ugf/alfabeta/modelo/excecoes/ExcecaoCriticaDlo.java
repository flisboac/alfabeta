/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.excecoes;

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
    
}
