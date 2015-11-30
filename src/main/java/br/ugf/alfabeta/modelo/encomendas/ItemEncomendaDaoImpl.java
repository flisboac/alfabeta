/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.entidades.JpaDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.livros.Livro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author flavio
 */
public class ItemEncomendaDaoImpl extends JpaDao<ItemEncomenda> implements ItemEncomendaDao {
    
    public ItemEncomendaDaoImpl() {
        super(ItemEncomenda.class);
    }
    
    @Override
    public boolean existe(ItemEncomenda itemEncomenda) throws ExcecaoDao {
        
        boolean retorno = super.existe(itemEncomenda);
        
        if (!retorno) {
            EntityManager manager = this.helper.getEntityManager();
            
            try {
                String jpql = "select x"
                        + " from " + ItemEncomenda.class.getName() + " x"
                        + " where x.encomenda = :encomenda"
                        + " and x.livro = :livro";
                TypedQuery<ItemEncomenda> query = manager.createQuery(jpql, ItemEncomenda.class);
                query.setParameter("encomenda", itemEncomenda.getEncomenda());
                query.setParameter("livro", itemEncomenda.getLivro());
                ItemEncomenda entidade = query.getSingleResult();
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
    public List<ItemEncomenda> listarPorEncomenda(Encomenda encomenda) throws ExcecaoDao {
        List<ItemEncomenda> retorno = null;
        Class<ItemEncomenda> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.encomenda = :encomenda";
        
        try {
            TypedQuery<ItemEncomenda> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("encomenda", encomenda);
            retorno = query.getResultList();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }

    @Override
    public List<ItemEncomenda> listarPorLivro(Livro livro) throws ExcecaoDao {
        List<ItemEncomenda> retorno = null;
        Class<ItemEncomenda> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.livro = :livro";
        
        try {
            TypedQuery<ItemEncomenda> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("livro", livro);
            retorno = query.getResultList();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }
    
}
