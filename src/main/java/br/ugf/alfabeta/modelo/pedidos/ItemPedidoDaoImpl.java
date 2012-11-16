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
    public List<ItemPedido> listarPorLivro(Livro livro) throws ExcecaoDao {
        List<ItemPedido> retorno = null;
        Class<ItemPedido> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.livro = :livro";
        
        try {
            manager.getTransaction().begin();
            TypedQuery<ItemPedido> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("livro", livro);
            retorno = query.getResultList();
            manager.getTransaction().commit();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
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
            manager.getTransaction().begin();
            TypedQuery<ItemPedido> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("pedido", pedido);
            retorno = query.getResultList();
            manager.getTransaction().commit();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
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
                + " where x.pendente = :pendente";
        
        try {
            manager.getTransaction().begin();
            TypedQuery<ItemPedido> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("pendente", true);
            retorno = query.getResultList();
            manager.getTransaction().commit();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
        }
        
        return retorno;
    }
}
