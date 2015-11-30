/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

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
public class ItemPedidoDaoImpl extends JpaDao<ItemPedido> implements ItemPedidoDao {
    
    public ItemPedidoDaoImpl() {
        super(ItemPedido.class);
    }

    @Override
    public boolean existe(ItemPedido itemPedido) throws ExcecaoDao {
        
        boolean retorno = super.existe(itemPedido);
        
        if (!retorno) {
            EntityManager manager = this.helper.getEntityManager();
            
            try {
                String jpql = "select x"
                        + " from " + ItemPedido.class.getName() + " x"
                        + " where x.pedido = :pedido"
                        + " and x.livro = :livro";
                TypedQuery<ItemPedido> query = manager.createQuery(jpql, ItemPedido.class);
                query.setParameter("pedido", itemPedido.getPedido());
                query.setParameter("livro", itemPedido.getLivro());
                ItemPedido entidade = query.getSingleResult();
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
    public List<ItemPedido> listarPorLivro(Livro livro) throws ExcecaoDao {
        List<ItemPedido> retorno = null;
        Class<ItemPedido> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.livro = :livro"
                + " and x.pedido.estado = :estado";
        
        try {
            TypedQuery<ItemPedido> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("livro", livro);
            query.setParameter("estado", EstadoPedido.Criado);
            retorno = query.getResultList();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao(e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }

    @Override
    public List<ItemPedido> listarPorPedido(Pedido pedido) throws ExcecaoDao {
        List<ItemPedido> retorno = null;
        Class<ItemPedido> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.pedido = :pedido";
        
        try {
            TypedQuery<ItemPedido> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("pedido", pedido);
            retorno = query.getResultList();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao(e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }

    @Override
    public List<ItemPedido> listarPendentesAtivos() throws ExcecaoDao {
        List<ItemPedido> retorno = null;
        Class<ItemPedido> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.pendente = :pendente"
                + " and x.pedido.";
        
        try {
            TypedQuery<ItemPedido> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("pendente", true);
            retorno = query.getResultList();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao(e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }
}
