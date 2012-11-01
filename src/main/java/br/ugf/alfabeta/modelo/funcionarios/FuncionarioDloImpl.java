/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.entidades.EntidadeDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;
import br.ugf.alfabeta.modelo.validacoes.Persistencia;

/**
 *
 * @author flavio
 */
public class FuncionarioDloImpl extends EntidadeDlo<Funcionario> implements FuncionarioDlo {
    
    
    public FuncionarioDloImpl() {
        super(new FuncionarioDaoImpl());
    }
    
    public FuncionarioDloImpl(FuncionarioDao dao) {
        super(dao);
    }
    
    @Override
    public void inserir(Funcionario funcionario) throws ExcecaoDlo {
        FuncionarioDao funcionarioDao = (FuncionarioDao) getDao();
        
        validar(funcionario, Persistencia.class);
        try {
            funcionarioDao.inserir(funcionario);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void alterar(Funcionario funcionario) throws ExcecaoDlo {
        FuncionarioDao funcionarioDao = (FuncionarioDao) getDao();
        
        validar(funcionario, Persistencia.class);
        try {
            funcionarioDao.alterar(funcionario);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void persistir(Funcionario funcionario) throws ExcecaoDlo {
        FuncionarioDao funcionarioDao = (FuncionarioDao) getDao();
        
        validar(funcionario, Persistencia.class);
        try {
            if (existe(funcionario)) {
                funcionarioDao.alterar(funcionario);
            } else {
                funcionarioDao.inserir(funcionario);
            }
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }

}
