/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import java.util.List;

/**
 *
 * @author Ana
 */
public interface Dao<T extends Entidade> {
    
    T obter(int id);
    boolean existe(T entidade);
    List<T> listar();
}
