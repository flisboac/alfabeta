/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import java.io.Serializable;

/**
 *
 * @author Ana
 */
public interface Entidade extends Cloneable, Serializable {
    
    public Long getId();
    public Object clone();
}
