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
    
    // Basic stuff
    T obter(Long id) throws ExcecaoDlo;
    boolean existe(T entidade) throws ExcecaoDlo;
    boolean existeId(Long id) throws ExcecaoDlo;
    List<T> listar() throws ExcecaoDlo;
    void validar(T entidade, Class<?>... gruposValidacao) throws ExcecaoDlo;
    
    // Why type erasure?
    Class<T> getClasseEntidade();
}
