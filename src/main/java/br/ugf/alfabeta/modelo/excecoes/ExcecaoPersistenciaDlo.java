/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.excecoes;

/**
 *
 * @author flavio
 */
public class ExcecaoPersistenciaDlo extends ExcecaoDlo {
    
    public ExcecaoPersistenciaDlo() {}
    public ExcecaoPersistenciaDlo(String msg) { super(msg); }
    public ExcecaoPersistenciaDlo(Throwable t) { super(t); }
    public ExcecaoPersistenciaDlo(String msg, Throwable t) { super(msg, t); }
}
