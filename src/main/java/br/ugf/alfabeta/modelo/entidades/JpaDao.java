/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import br.ugf.alfabeta.modelo.util.JpaHelper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Ana
 */
public class JpaDao<T extends Entidade> implements Dao<T> {
    
    protected JpaHelper helper = new JpaHelper();
    protected Class<T> classe;
    
    public JpaDao() {}
    
    public JpaDao(Class<T> classe) {
        this.classe = classe;
    }
    
    @Override
    public T obter(int id) {
        T retorno = null;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.getTransaction().begin();
            retorno = manager.find(this.classe, id);
            manager.getTransaction().commit();
                    
        } catch (PersistenceException ex) {
            logErro("Erro ao obter entidade '" + this.classe.getName() + "' com ID " + id + ".", ex);
            
        }
        
        return retorno;
    }
    
    public boolean inserir(T entidade) {
        boolean retorno = false;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.getTransaction().begin();
            manager.persist(entidade);
            manager.getTransaction().commit();
            retorno = true;
            
        } catch (PersistenceException e) {
            logErro("Erro ao inserir nova entidade '" + this.classe.getName() + "'.", e);
        }
        
        return retorno;
    }
    
    public boolean alterar(T entidade) {
        boolean retorno = false;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.getTransaction().begin();
            manager.merge(entidade);
            manager.getTransaction().commit();
            retorno = true;
            
        } catch (PersistenceException e) {
            logErro("Erro ao alterar dados da entidade '" + this.classe.getName() + "'.", e);
        }
        
        return retorno;
    }
    
    public boolean excluir(T entidade) {    
        boolean retorno = false;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.getTransaction().begin();
            manager.remove(entidade);
            manager.getTransaction().commit();
            retorno = true;
            
        } catch (PersistenceException e) {
            logErro("Erro ao excluir entidade '" + this.classe.getName() + "'.", e);
        }
        
        return retorno;
    }
    
    @Override
    public boolean existe(T entidade) {
        boolean retorno = false;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.getTransaction().begin();
            retorno = manager.contains(entidade);
            manager.getTransaction().commit();
            
        } catch (PersistenceException e) {
            logErro("Erro ao verificar a existência da entidade '" + this.classe.getName() + "'.", e);
        }
        
        return retorno;
    }
    
    @Override
    public List<T> listar() {
        List<T> retorno = null;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(this.classe);
            query.select(query.from(this.classe));
            retorno = manager.createQuery(query).getResultList();
            
        } catch (PersistenceException e) {
            logErro("Erro ao listar entidades '" + this.classe.getName() + "'.", e);
        }
        
        return retorno;
    }
    
    protected void logErro(String msg, Throwable t) {
        Logger.getLogger(this.classe.getName()).log(Level.SEVERE, msg, t);
    }
}
