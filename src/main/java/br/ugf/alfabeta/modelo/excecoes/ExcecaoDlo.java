/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.excecoes;

/**
 *
 * @author Ana
 */
public class ExcecaoDlo extends Exception {
    
    public ExcecaoDlo() {}
    public ExcecaoDlo(String msg) { super(msg); }
    public ExcecaoDlo(Throwable t) { super(t); }
}
