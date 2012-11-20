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
    protected Validador<T> validador;
    
    public EntidadeDlo() {}
    
    public EntidadeDlo(Dao<T> dao) {
        this.dao = dao;
        this.validador = new ValidadorEntidade<T>(dao.getClasseEntidade());
    }
    
    public EntidadeDlo(Validador<T> validador) {
        this.validador = validador;
    }
    
    public EntidadeDlo(Dao<T> dao, Validador<T> validador) {
        this.dao = dao;
        this.validador = validador;
    }
    
    @Override
    public Validador<T> getValidador() {
        return this.validador;
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
                String mensagem = "";
                String sep = "";
                
                for (Object erro : erros) {
                    ConstraintViolation<?> violation = (ConstraintViolation<?>) erro;
                    mensagem += sep + violation.getMessage();
                    sep = "<br />";
                }
                
                throw new ExcecaoCriticaDlo(erros, mensagem);
                
            } else {
                for (Object erro : erros) {
                    ConstraintViolation<?> violation = (ConstraintViolation<?>) erro;
                    throw new ExcecaoCriticaDlo(erros, violation.getMessage());
                }
            }
        }
    }
    
    @Override
    public void validarCampo(T entidade, String campo, Class<?>... grupos) throws ExcecaoDlo {
        
        Validator validator = validatorFactory.getValidator();
        Set erros = validator.validateProperty(entidade, campo, grupos);
        
        if (erros != null) {
            if (erros.size() > 1) {
                String mensagem = "";
                String sep = "";
                
                for (Object erro : erros) {
                    ConstraintViolation<?> violation = (ConstraintViolation<?>) erro;
                    mensagem += sep + violation.getMessage();
                    sep = "<br />";
                }
                
                throw new ExcecaoCriticaDlo(erros, mensagem);
                
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
    public T obterCompleto(Long id) throws ExcecaoDlo {
        return obter(id);
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

    @Override
    public List<T> listarOrdenado(OrdemListagem ordem, String... campos) throws ExcecaoDlo {
        
        List<T> retorno = null;
        Dao<T> entidadeDao = getDao();
        
        if (ordem == null) {
            throw new ExcecaoCriticaDlo("Ordem não deve ser nula.");
        }
        
        if (campos == null) {
            campos = new String[0];
        }
        
        try {
            retorno = entidadeDao.listarOrdenado(ordem, campos);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex);
        }
        
        if (retorno == null) {
            throw new ExcecaoPersistenciaDlo("Lista nula retornada.");
        }
        
        return retorno;
    }
    
    
}
