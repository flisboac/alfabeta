/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.editoras;

import br.ugf.alfabeta.modelo.entidades.DloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author Ana
 */
public interface EditoraDlo extends DloPersistencia<Editora> {

    public Editora obterPorCodigo(String codigo) throws ExcecaoDlo;
}
