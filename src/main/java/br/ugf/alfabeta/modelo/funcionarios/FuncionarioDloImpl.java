/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.clientes.ClienteDao;
import br.ugf.alfabeta.modelo.clientes.ClienteDloImpl;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;

/**
 *
 * @author flavio
 */
public class FuncionarioDloImpl extends ClienteDloImpl<Funcionario> implements FuncionarioDlo {
    
    public FuncionarioDloImpl() {
        super(new FuncionarioDaoImpl(), new ValidadorFuncionario());
    }
    
    public FuncionarioDloImpl(FuncionarioDao dao) {
        super(dao, new ValidadorFuncionario());
    }

    @Override
    public Funcionario obterPorMatricula(String matricula) throws ExcecaoDlo {
        
        if (matricula == null || matricula.isEmpty()) {
            throw new ExcecaoCriticaDlo("Matrícula não pode ser nula ou vazia.");
        }
        
        FuncionarioDao funcionarioDao = (FuncionarioDao)getDao();
        Funcionario retorno = null;
        try {
            retorno = funcionarioDao.obterPorMatricula(matricula);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(
                    "Erro ao obter funcionário com matrícula '" + matricula + "'.", ex);
        }
        
        return retorno;
    }
}
