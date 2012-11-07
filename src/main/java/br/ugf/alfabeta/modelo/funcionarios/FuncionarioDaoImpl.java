/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.entidades.JpaDao;

/**
 *
 * @author flavio
 */
public class FuncionarioDaoImpl extends JpaDao<Funcionario> implements FuncionarioDao {
    
    public FuncionarioDaoImpl() {
        super(Funcionario.class);
    }
}
