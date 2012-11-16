/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.livros;

import br.ugf.alfabeta.modelo.entidades.JpaDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import java.util.List;
import javax.persistence.EntityManager;
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
    public List<Livro> listarEmFalta() throws ExcecaoDao {
        List<Livro> retorno = null;
        Class<Livro> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.quantidade < x.quantidadeMinima";
        
        try {
            manager.getTransaction().begin();
            TypedQuery<Livro> query = manager.createQuery(jql, classeEntidade);
            retorno = query.getResultList();
            manager.getTransaction().commit();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
        }
        
        return retorno;
    }
}
