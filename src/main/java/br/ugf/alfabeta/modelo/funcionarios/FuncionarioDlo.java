/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author flavio
 */
public interface FuncionarioDlo extends Dlo<Funcionario> {
    
    void inserir(Funcionario funcionario) throws ExcecaoDlo;
    void alterar(Funcionario funcionario) throws ExcecaoDlo;
    void persistir(Funcionario funcionario) throws ExcecaoDlo;
}
