/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.clientes.ClienteDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;

/**
 *
 * @author flavio
 */
public interface FuncionarioDao extends ClienteDao<Funcionario> {

    public Funcionario obterPorMatricula(String matricula) throws ExcecaoDao;
}
