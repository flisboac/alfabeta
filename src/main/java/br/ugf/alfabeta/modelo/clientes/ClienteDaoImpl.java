/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.JpaDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.pedidos.Pedido;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ana
 */
public class ClienteDaoImpl<T extends Cliente> extends JpaDao<T> implements ClienteDao<T> {
    
    public ClienteDaoImpl() {
        this((Class<T>) Cliente.class);
    }
    
    public ClienteDaoImpl(Class<T> classeEntidade) {
        super(classeEntidade);
    }
    
    @Override
    public T obterCompleto(Long id) throws ExcecaoDao {
        
        T retorno = null;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            retorno = manager.find(this.classe, id);
            retorno.getPedidos().size();
            //String jpql = "select x from Pedido x"
            //        + " where x.clienteCriador = :cliente";
            //TypedQuery<Pedido> query = manager.createQuery(jpql, Pedido.class);
            //retorno.setPedidos(query.getResultList());
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao atualizar entidade '" + this.classe.getName() + "'.", e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }
    
    @Override
    public boolean existe(T cliente) throws ExcecaoDao {
        
        boolean retorno = super.existe(cliente);
        
        if (!retorno) {
            EntityManager manager = this.helper.getEntityManager();
            
            try {
                String jpql = "select x"
                        + " from " + Cliente.class.getName() + " x"
                        + " where x.email = :email";
                TypedQuery<Cliente> query = manager.createQuery(jpql, Cliente.class);
                query.setParameter("email", cliente.getEmail());
                Cliente entidade = query.getSingleResult();
                retorno = true;
                
            } catch (NoResultException e) {
                retorno = false;
                
            } catch (PersistenceException e) {
                throw new ExcecaoDao(e);
                
            } finally {
                manager.close();
            }
        }
        return retorno;
    }
    
    @Override
    public T obterPorEmail(String email) throws ExcecaoDao {
        T retorno = null;
        Class<T> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.email = :email";
        
        try {
            TypedQuery<T> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("email", email);
            retorno = query.getSingleResult();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }
}
