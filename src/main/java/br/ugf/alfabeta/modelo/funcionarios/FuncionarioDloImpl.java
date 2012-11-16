/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;

/**
 *
 * @author flavio
 */
public class FuncionarioDloImpl extends EntidadeDloPersistencia<Funcionario> implements FuncionarioDlo {
    
    public FuncionarioDloImpl() {
        super(new FuncionarioDaoImpl(), new ValidadorFuncionario());
    }
    
    public FuncionarioDloImpl(FuncionarioDao dao) {
        super(dao, new ValidadorFuncionario());
    }
}
