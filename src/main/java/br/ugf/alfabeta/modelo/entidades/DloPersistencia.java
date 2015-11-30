/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author Ana
 */
public interface DloPersistencia<T extends Entidade> extends Dlo<T> {
    
    T inserir(T entidade) throws ExcecaoDlo;
    T alterar(T entidade) throws ExcecaoDlo;
    T persistir(T entidade) throws ExcecaoDlo;
    void excluir(T entidade) throws ExcecaoDlo;
}
