/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import java.util.List;

/**
 *
 * @author Ana
 */
public interface Dlo<T extends Entidade> {
    
    T obter(int id) throws ExcecaoDlo;
    boolean existe(T entidade) throws ExcecaoDlo;
    List<T> listar() throws ExcecaoDlo;
}