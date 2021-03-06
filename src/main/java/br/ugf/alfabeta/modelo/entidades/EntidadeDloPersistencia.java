/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;


import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;
import br.ugf.alfabeta.modelo.validacoes.Identidade;
import br.ugf.alfabeta.modelo.validacoes.Identificacao;
import br.ugf.alfabeta.modelo.validacoes.Persistencia;

/**
 *
 * @author Ana
 */
public class EntidadeDloPersistencia<T extends Entidade> extends EntidadeDlo<T> implements DloPersistencia<T> {

    public EntidadeDloPersistencia() {}
    
    public EntidadeDloPersistencia(Dao<T> dao) {
        super(dao);
    }
    
    public EntidadeDloPersistencia(Validador<T> validador) {
        super(validador);
    }
    
    public EntidadeDloPersistencia(Dao<T> dao, Validador<T> validador) {
        super(dao, validador);
    }
    
    @Override
    public T inserir(T entidade) throws ExcecaoDlo {
        Dao<T> entidadeDao = (Dao<T>) getDao();
        
        validar(entidade, Identidade.class);
        try {
            entidade = entidadeDao.inserir(entidade);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
        
        return entidade;
    }
    
    @Override
    public T alterar(T entidade) throws ExcecaoDlo {
        Dao<T> entidadeDao = (Dao<T>) getDao();
        
        validar(entidade, Persistencia.class);
        try {
            entidade = entidadeDao.alterar(entidade);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
        
        return entidade;
    }
    
    @Override
    public T persistir(T entidade) throws ExcecaoDlo {
        Dao<T> entidadeDao = (Dao<T>) getDao();
        
        if (entidade != null) {
            try {
                if (entidade.getId() != null) {
                    if (existeId(entidade.getId())) {
                        // Já existe uma entidade com o ID passado, 
                        // alterar a existente
                        entidade = entidadeDao.alterar(entidade);
                        
                    } else {
                        // ID inválido passado, lançar erro!
                        throw new ExcecaoCriticaDlo("Não há entidade com o ID '" + entidade.getId() + "' passado.");
                    }
                    
                } else {
                    // Se não há ID, considera-se que a entidade ainda não tenha
                    // sido persistida.
                    
                    if (existe(entidade)) {
                        // Já existe uma entidade com a
                        // identidade passada, lançar erro.
                        throw new ExcecaoCriticaDlo("Já existe outra entidade com a identidade passada.");
                        
                    } else {
                        entidade = entidadeDao.inserir(entidade);
                    }
                }

            } catch (ExcecaoDao ex) {
                throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
            }
        }
        
        return entidade;
    }
    
    @Override
    public void excluir(T entidade) throws ExcecaoDlo {
        Dao<T> entidadeDao = (Dao<T>) getDao();
        
        validar(entidade, Identificacao.class);
        try {
            entidadeDao.excluir(entidade);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
}
