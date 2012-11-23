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
public interface Entidade<T extends Entidade> extends Cloneable, Serializable {
    
    public Long getId();
    public T clone();
    public T clone(T entidade);
}
