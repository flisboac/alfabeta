/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;
import br.ugf.alfabeta.modelo.validacoes.Consulta;
import br.ugf.alfabeta.modelo.validacoes.Persistencia;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author flavio
 */
public class EntidadeDlo<T extends Entidade> implements Dlo<T> {
    
    protected ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    protected Dao<T> dao;
    
    public EntidadeDlo() {}
    
    public EntidadeDlo(Dao<T> dao) {
        this.dao = dao;
    }
    
    protected Dao<T> getDao() {
        return this.dao;
    }
    
    @Override
    public Class<T> getClasseEntidade() {
        return getDao().getClasseEntidade();
    }
    
    @Override
    public void validar(T entidade, Class<?>... grupos) throws ExcecaoDlo {
        
        Validator validator = validatorFactory.getValidator();
        Set erros = validator.validate(entidade, grupos);
        
        if (erros != null) {
            if (erros.size() > 1) {
                throw new ExcecaoCriticaDlo(erros, "Múltiplos erros de validação.");
                
            } else {
                for (Object erro : erros) {
                    ConstraintViolation<?> violation = (ConstraintViolation<?>) erro;
                    throw new ExcecaoCriticaDlo(erros, violation.getMessage());
                }
            }
        }
    }
    
    @Override
    public T obter(Long id) throws ExcecaoDlo {
        T retorno = null;
        Dao<T> entidadeDao = getDao();
        
        if (id == null) {
            throw new ExcecaoCriticaDlo("ID não pode ser nulo.");
        }
        
        try {
            retorno = entidadeDao.obter(id);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
        
        if (retorno == null || retorno.getId() == null) {
            throw new ExcecaoPersistenciaDlo("Valor inválido retornado da camada de persistência.");
        }
        
        //validar(retorno, Persistencia.class);
        
        return retorno;
    }

    @Override
    public boolean existe(T entidade) throws ExcecaoDlo {
        boolean retorno = false;
        Dao<T> entidadeDao = getDao();
        
        validar(entidade, Consulta.class);
        
        try {
            retorno = entidadeDao.existe(entidade);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
        
        return retorno;
    }

    @Override
    public boolean existeId(Long id) throws ExcecaoDlo {
        boolean retorno = false;
        Dao<T> entidadeDao = getDao();
        
        if (id == null) {
            throw new ExcecaoCriticaDlo("ID não pode ser nulo.");
        }
        
        try {
            retorno = entidadeDao.existeId(id);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
        
        return retorno;
    }

    @Override
    public List<T> listar() throws ExcecaoDlo {
        List<T> retorno = null;
        Dao<T> entidadeDao = getDao();
        
        try {
            retorno = entidadeDao.listar();
            
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
