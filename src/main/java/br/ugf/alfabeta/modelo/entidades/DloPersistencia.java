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
    
    void inserir(T entidade) throws ExcecaoDlo;
    void alterar(T entidade) throws ExcecaoDlo;
    void persistir(T entidade) throws ExcecaoDlo;
    void excluir(T entidade) throws ExcecaoDlo;
}
