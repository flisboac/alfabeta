/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author flavio
 */
public interface Validador<T extends Entidade> {

    void validarId(Long id) throws ExcecaoDlo;
    void validarIdentidade(T entidade) throws ExcecaoDlo;
    void validarParaConsulta(T entidade) throws ExcecaoDlo;
    void validarParaPersistencia(T entidade) throws ExcecaoDlo;
}
