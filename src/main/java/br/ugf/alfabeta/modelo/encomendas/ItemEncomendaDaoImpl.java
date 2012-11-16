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
    public List<ItemEncomenda> listarPorEncomenda(Encomenda encomenda) throws ExcecaoDao {
        List<ItemEncomenda> retorno = null;
        Class<ItemEncomenda> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.encomenda = :encomenda";
        
        try {
            manager.getTransaction().begin();
            TypedQuery<ItemEncomenda> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("encomenda", encomenda);
            retorno = query.getResultList();
            manager.getTransaction().commit();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
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
            manager.getTransaction().begin();
            TypedQuery<ItemEncomenda> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("livro", livro);
            retorno = query.getResultList();
            manager.getTransaction().commit();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
        }
        
        return retorno;
    }
    
}
