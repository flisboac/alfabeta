/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author flavio
 */
public class ValidadorEntidade<T extends Entidade> implements Validador<T> {

    protected ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private Class<T> classeEntidade;
    
    public ValidadorEntidade() {}
    
    public ValidadorEntidade(Class<T> classeEntidade) {
        this.classeEntidade = classeEntidade;
    }
    
    @Override
    public Class<T> getClasseEntidade() {
        return this.classeEntidade;
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
    
}
