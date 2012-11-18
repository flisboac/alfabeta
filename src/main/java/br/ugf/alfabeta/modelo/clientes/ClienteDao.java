/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.Dao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;

/**
 *
 * @author flavio
 */
public interface ClienteDao<T extends Cliente> extends Dao<T> {
    
    public T obterPorEmail(String email) throws ExcecaoDao;
}
