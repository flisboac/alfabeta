/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author flavio
 */
public class ValidadorEntidade<T extends Entidade> implements Validador<T> {
    
    protected Class<T> classeEntidade;
    
    public ValidadorEntidade() {}
    
    public ValidadorEntidade(Class<T> classeEntidade) {
        this.classeEntidade = classeEntidade;
    }
    
    public Class<T> getClasseEntidade() {
        return this.classeEntidade;
    }
    
    @Override
    public void validarId(Long id) throws ExcecaoDlo {
        
        if (id == null) {
            throw new ExcecaoCriticaDlo("ID da entidade não pode ser nulo.");
        }
    }

    @Override
    public void validarIdentidade(T entidade) throws ExcecaoDlo {
        
        if (entidade == null) {
            throw new ExcecaoCriticaDlo("Entidade não pode ser nula.");
        }
        
        if (!entidade.isIdentidadeValida()) {
            throw new ExcecaoCriticaDlo("Identidade da entidade '" + getClasseEntidade().getName() + "' é inválida.");
        }
    }
    
    @Override
    public void validarParaConsulta(T entidade) throws ExcecaoDlo {
        
        if (entidade == null) {
            throw new ExcecaoCriticaDlo("Entidade não pode ser nula.");
        }
        
        validarId(entidade.getId());
    }

    @Override
    public void validarParaPersistencia(T entidade) throws ExcecaoDlo {
        
        if (entidade == null) {
            throw new ExcecaoCriticaDlo("Entidade não pode ser nula.");
        }
        
        validarId(entidade.getId());
        validarIdentidade(entidade);
    }
}
