/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.livros;

import br.ugf.alfabeta.modelo.editoras.Editora;
import br.ugf.alfabeta.modelo.entidades.JpaDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author ubuntu
 */
public class LivroDaoImpl extends JpaDao<Livro> implements LivroDao {
    
    public LivroDaoImpl() {
        super(Livro.class);
    }
    
    @Override
    public boolean existe(Livro livro) throws ExcecaoDao {
        
        boolean retorno = super.existe(livro);
        
        if (!retorno) {
            EntityManager manager = this.helper.getEntityManager();
            
            try {
                String jpql = "select x"
                        + " from " + Livro.class.getName() + " x"
                        + " where x.codigo = :codigo";
                TypedQuery<Livro> query = manager.createQuery(jpql, Livro.class);
                query.setParameter("codigo", livro.getCodigo());
                Livro entidade = query.getSingleResult();
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
    public List<Livro> listarEmFalta() throws ExcecaoDao {
        List<Livro> retorno = null;
        Class<Livro> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.quantidade < x.quantidadeMinima";
        
        try {
            TypedQuery<Livro> query = manager.createQuery(jql, classeEntidade);
            retorno = query.getResultList();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }

    @Override
    public List<Livro> listarPorEditora(Editora editora) throws ExcecaoDao {
        List<Livro> retorno = null;
        Class<Livro> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.editora < x.editora";
        
        try {
            TypedQuery<Livro> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("editora", editora);
            retorno = query.getResultList();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }
}
