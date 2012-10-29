/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.entidades.DloAbstrato;
import br.ugf.alfabeta.modelo.entidades.Validador;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;

/**
 *
 * @author flavio
 */
public class FuncionarioDloImpl extends DloAbstrato<Funcionario> implements FuncionarioDlo {
    
    private Validador<Funcionario> validador = new ValidadorFuncionario();
    private FuncionarioDao dao;
    
    public FuncionarioDloImpl() {
        this.dao = new FuncionarioDaoImpl();
    }
    
    public FuncionarioDloImpl(FuncionarioDao dao) {
        this.dao = dao;
    }
    
    @Override
    public FuncionarioDao getDao() {
        return this.dao;
    }

    @Override
    protected Validador<Funcionario> getValidador() {
        return this.validador;
    }

    @Override
    public void inserir(Funcionario funcionario) throws ExcecaoDlo {
        FuncionarioDao funcionarioDao = getDao();
        
        getValidador().validarParaPersistencia(funcionario);
        try {
            funcionarioDao.inserir(funcionario);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void alterar(Funcionario cliente) throws ExcecaoDlo {
        FuncionarioDao funcionarioDao = getDao();
        
        getValidador().validarParaPersistencia(cliente);
        try {
            funcionarioDao.alterar(cliente);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void persistir(Funcionario cliente) throws ExcecaoDlo {
        FuncionarioDao funcionarioDao = getDao();
        
        getValidador().validarParaPersistencia(cliente);
        try {
            if (existe(cliente)) {
                funcionarioDao.alterar(cliente);
            } else {
                funcionarioDao.inserir(cliente);
            }
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }

}
