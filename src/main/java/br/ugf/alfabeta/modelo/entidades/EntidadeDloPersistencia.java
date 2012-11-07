/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;


import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;
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
    
    @Override
    public void inserir(T entidade) throws ExcecaoDlo {
        Dao<T> entidadeDao = (Dao<T>) getDao();
        
        validar(entidade, Persistencia.class);
        try {
            entidadeDao.inserir(entidade);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void alterar(T entidade) throws ExcecaoDlo {
        Dao<T> entidadeDao = (Dao<T>) getDao();
        
        validar(entidade, Persistencia.class);
        try {
            entidadeDao.alterar(entidade);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void persistir(T entidade) throws ExcecaoDlo {
        Dao<T> entidadeDao = (Dao<T>) getDao();
        
        validar(entidade, Persistencia.class);
        try {
            if (existe(entidade)) {
                entidadeDao.alterar(entidade);
            } else {
                entidadeDao.inserir(entidade);
            }
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void excluir(T entidade) throws ExcecaoDlo {
        Dao<T> entidadeDao = (Dao<T>) getDao();
        
        validar(entidade, Persistencia.class);
        try {
            entidadeDao.excluir(entidade);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
}
