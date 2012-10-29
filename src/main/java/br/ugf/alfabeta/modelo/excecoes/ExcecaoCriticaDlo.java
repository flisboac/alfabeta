/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.excecoes;

/**
 *
 * @author flavio
 */
public class ExcecaoCriticaDlo extends ExcecaoDlo {
    
    public ExcecaoCriticaDlo() {}
    public ExcecaoCriticaDlo(String msg) { super(msg); }
    public ExcecaoCriticaDlo(Throwable t) { super(t); }
    public ExcecaoCriticaDlo(String msg, Throwable t) { super(msg, t); }
}
