/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.excecoes;

/**
 *
 * @author Ana
 */
public class ExcecaoDlo extends Excecao {
    
    public ExcecaoDlo() {}
    public ExcecaoDlo(String msg) { super(msg); }
    public ExcecaoDlo(Throwable t) { super(t); }
    public ExcecaoDlo(String msg, Throwable t) { super(msg, t); }
}
