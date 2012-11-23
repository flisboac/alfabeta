/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.entidades.JpaDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author ubuntu
 */
public class EncomendaDaoImpl extends JpaDao<Encomenda> implements EncomendaDao {
    
    public EncomendaDaoImpl() {
        super(Encomenda.class);
    }
    
    @Override
    public void atualizar(Encomenda encomenda) throws ExcecaoDao {
        super.atualizar(encomenda);
        
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.refresh(encomenda);
            encomenda.getItens().size();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao atualizar entidade '" + this.classe.getName() + "'.", e);
            
        } finally {
            manager.close();
        }
    }
    
    @Override
    public boolean existe(Encomenda encomenda) throws ExcecaoDao {
        
        boolean retorno = super.existe(encomenda);
        
        if (!retorno) {
            EntityManager manager = this.helper.getEntityManager();
            
            try {
                String jpql = "select x"
                        + " from " + Encomenda.class.getName() + " x"
                        + " where x.codigo = :codigo";
                TypedQuery<Encomenda> query = manager.createQuery(jpql, Encomenda.class);
                query.setParameter("codigo", encomenda.getCodigo());
                Encomenda entidade = query.getSingleResult();
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
}
