/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.editoras;

import br.ugf.alfabeta.modelo.entidades.JpaDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ana
 */
public class EditoraDaoImpl extends JpaDao<Editora> implements EditoraDao {
    
    public EditoraDaoImpl() {
        super(Editora.class);
    }

    @Override
    public Editora obterCompleto(Long id) throws ExcecaoDao {
        
        Editora retorno = null;
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            retorno = manager.find(this.classe, id);
            retorno.getLivros().size();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao atualizar entidade '" + this.classe.getName() + "'.", e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }
    
    @Override
    public void atualizar(Editora editora) throws ExcecaoDao {
        super.atualizar(editora);
        
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.refresh(editora);
            editora.getLivros().size();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao atualizar entidade '" + this.classe.getName() + "'.", e);
            
        } finally {
            manager.close();
        }
    }
    
    @Override
    public boolean existe(Editora editora) throws ExcecaoDao {
        
        boolean retorno = super.existe(editora);
        
        if (!retorno) {
            EntityManager manager = this.helper.getEntityManager();
            
            try {
                String jpql = "select x"
                        + " from " + Editora.class.getName() + " x"
                        + " where x.codigo = :codigo";
                TypedQuery<Editora> query = manager.createQuery(jpql, Editora.class);
                query.setParameter("codigo", editora.getCodigo());
                Editora entidade = query.getSingleResult();
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
    public Editora obterPorCodigo(String codigo) throws ExcecaoDao {
        Editora retorno = null;
        Class<Editora> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.codigo = :codigo";
        
        try {
            TypedQuery<Editora> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("codigo", codigo);
            retorno = query.getSingleResult();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }
}
