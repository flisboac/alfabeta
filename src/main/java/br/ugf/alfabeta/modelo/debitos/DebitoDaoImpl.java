/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.debitos;

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
public class DebitoDaoImpl extends JpaDao<Debito> implements DebitoDao {
    
    public DebitoDaoImpl() {
        super(Debito.class);
    }
    
    @Override
    public boolean existe(Debito debito) throws ExcecaoDao {
        
        boolean retorno = super.existe(debito);
        
        if (!retorno) {
            EntityManager manager = this.helper.getEntityManager();
            
            try {
                String jpql = "select x"
                        + " from " + Debito.class.getName() + " x"
                        + " where x.pedido = :pedido";
                TypedQuery<Debito> query = manager.createQuery(jpql, Debito.class);
                query.setParameter("pedido", debito.getPedido());
                Debito entidade = query.getSingleResult();
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
