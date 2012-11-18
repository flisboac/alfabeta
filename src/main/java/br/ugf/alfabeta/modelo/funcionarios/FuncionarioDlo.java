/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.entidades.DloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author flavio
 */
public interface FuncionarioDlo extends DloPersistencia<Funcionario> {

    public Funcionario obterPorMatricula(String matricula) throws ExcecaoDlo;
}
