/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.excecoes;

/**
 *
 * @author flavio
 */
public class ExcecaoDao extends Excecao {
    
    public ExcecaoDao() {}
    public ExcecaoDao(String msg) { super(msg); }
    public ExcecaoDao(Throwable t) { super(t); }
    public ExcecaoDao(String msg, Throwable t) { super(msg, t); }
}
