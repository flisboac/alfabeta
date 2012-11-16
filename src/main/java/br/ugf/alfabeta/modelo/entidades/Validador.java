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
    
    Class<T> getClasseEntidade();
    void validar(T entidade, Class<?>... gruposValidacao) throws ExcecaoDlo;
    void validarCampo(T entidade, String nome, Class<?>... gruposValidacao) throws ExcecaoDlo;
}
