/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.editoras;

import br.ugf.alfabeta.modelo.entidades.Dao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;

/**
 *
 * @author Ana
 */
public interface EditoraDao extends Dao<Editora> {

    public Editora obterPorCodigo(String codigo) throws ExcecaoDao;
}
