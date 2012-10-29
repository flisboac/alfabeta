/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.entidades;

import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.util.JpaHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

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
    public T obter(Long id) throws ExcecaoDao {
        T retorno = null;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            retorno = manager.find(this.classe, id);
                    
        } catch (PersistenceException ex) {
            throw new ExcecaoDao("Erro ao obter entidade '" + this.classe.getName() + "' com ID " + id + ".", ex);
            
        }
        
        return retorno;
    }
    
    @Override
    public void inserir(T entidade) throws ExcecaoDao {
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.getTransaction().begin();
            manager.persist(entidade);
            manager.getTransaction().commit();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao inserir nova entidade '" + this.classe.getName() + "'.", e);
        }
    }
    
    @Override
    public void alterar(T entidade) throws ExcecaoDao {
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.getTransaction().begin();
            manager.merge(entidade);
            manager.getTransaction().commit();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao alterar dados da entidade '" + this.classe.getName() + "'.", e);
        }
    }
    
    @Override
    public void excluir(T entidade) throws ExcecaoDao {
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.getTransaction().begin();
            manager.remove(entidade);
            manager.getTransaction().commit();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao excluir entidade '" + this.classe.getName() + "'.", e);
        }
    }
    
    @Override
    public boolean existe(T entidade) throws ExcecaoDao {
        boolean retorno = false;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.getTransaction().begin();
            retorno = manager.contains(entidade);
            manager.getTransaction().commit();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao verificar a existência da entidade '" + this.classe.getName() + "'.", e);
        }
        
        return retorno;
    }
    
    @Override
    public boolean existeId(Long id) throws ExcecaoDao {
        boolean retorno = false;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            retorno = manager.find(this.classe, id) != null;
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao verificar a existência do ID " + id + "para a entidade '" + this.classe.getName() + "'.", e);
        }
        
        return retorno;
    }
    
    @Override
    public List<T> listar() throws ExcecaoDao {
        List<T> retorno = null;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            retorno = manager.createQuery("from " + this.classe.getName()).getResultList();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao listar entidades '" + this.classe.getName() + "'.", e);
        }
        
        return retorno;
    }
    
    @Override
    public Class<T> getClasseEntidade() {
        return this.classe;
    }
}
