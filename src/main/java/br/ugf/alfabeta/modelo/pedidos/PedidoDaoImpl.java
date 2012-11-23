/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

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
public class PedidoDaoImpl extends JpaDao<Pedido> implements PedidoDao {

    public PedidoDaoImpl() {
        super(Pedido.class);
    }
    
    @Override
    public void atualizar(Pedido pedido) throws ExcecaoDao {
        super.atualizar(pedido);
        
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.refresh(pedido);
            pedido.getItens().size();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao atualizar entidade '" + this.classe.getName() + "'.", e);
            
        } finally {
            manager.close();
        }
    }
    
    @Override
    public boolean existe(Pedido pedido) throws ExcecaoDao {
        
        boolean retorno = super.existe(pedido);
        
        if (!retorno) {
            EntityManager manager = this.helper.getEntityManager();
            
            try {
                String jpql = "select x"
                        + " from " + Pedido.class.getName() + " x"
                        + " where x.codigo = :codigo"
                        + " and x.clienteCriador = :clienteCriador";
                TypedQuery<Pedido> query = manager.createQuery(jpql, Pedido.class);
                query.setParameter("codigo", pedido.getCodigo());
                query.setParameter("clienteCriador", pedido.getClienteCriador());
                Pedido entidade = query.getSingleResult();
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
