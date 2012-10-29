/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;
import java.util.List;

/**
 *
 * @author flavio
 */
public abstract class DloAbstrato<T extends Entidade> implements Dlo<T> {
    
    protected abstract Validador<T> getValidador();
    protected abstract Dao<T> getDao();
    
    @Override
    public Class<T> getClasseEntidade() {
        return getDao().getClasseEntidade();
    }
    
    @Override
    public T obter(Long id) throws ExcecaoDlo {
        T retorno = null;
        Dao<T> dao = getDao();
        
        getValidador().validarId(id);
        
        try {
            retorno = dao.obter(id);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
        
        if (retorno == null || retorno.getId() == null || !retorno.isIdentidadeValida()) {
            throw new ExcecaoPersistenciaDlo("Valor inválido retornado da camada de persistência.");
        }
        
        return retorno;
    }

    @Override
    public boolean existe(T entidade) throws ExcecaoDlo {
        boolean retorno = false;
        Dao<T> dao = getDao();
        
        getValidador().validarParaConsulta(entidade);
        try {
            retorno = dao.existe(entidade);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
        
        return retorno;
    }

    @Override
    public boolean existeId(Long id) throws ExcecaoDlo {
        boolean retorno = false;
        Dao<T> dao = getDao();
        
        getValidador().validarId(id);
        try {
            retorno = dao.existeId(id);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
        
        return retorno;
    }

    @Override
    public List<T> listar() throws ExcecaoDlo {
        List<T> retorno = null;
        Dao<T> dao = getDao();
        
        try {
            retorno = dao.listar();
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
        
        // O retorno de uma lista nula é considerado comportamento anormal.
        // A exceção por si só já deve ser suficiente para verificar a validade
        // do valor de retorno. Forçar que o código-cliente também verifique
        // que o valor de retorno é válido é querer demais...
        if (retorno == null) {
            throw new ExcecaoPersistenciaDlo("Valor inválido retornado da camada de persistência.");
        }
        
        return retorno;
    }
    
    
}
