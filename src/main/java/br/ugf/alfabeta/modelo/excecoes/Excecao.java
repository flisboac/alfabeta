/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.excecoes;

/**
 *
 * @author flavio
 */
public class Excecao extends Exception {
    
    public Excecao() {}
    public Excecao(String msg) { super(msg); }
    public Excecao(Throwable t) { super(t); }
    public Excecao(String msg, Throwable t) { super(msg, t); }
    
}
