/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import java.util.List;

/**
 *
 * @author flavio
 */
public interface Dao<T extends Entidade> {

    T obter(Long id) throws ExcecaoDao;
    T obterCompleto(Long id) throws ExcecaoDao;
    T inserir(T entidade) throws ExcecaoDao;
    T alterar(T entidade) throws ExcecaoDao;
    void excluir(T entidade) throws ExcecaoDao;
    T atualizar(T entidade) throws ExcecaoDao;
    boolean existe(T entidade) throws ExcecaoDao;
    boolean existeId(Long id) throws ExcecaoDao;
    List<T> listar() throws ExcecaoDao;
    List<T> listarOrdenado(OrdemListagem ordem, String... campos) throws ExcecaoDao;
    Class<T> getClasseEntidade();
}
