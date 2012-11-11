/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.JpaDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ana
 */
public class ClienteDaoImpl extends JpaDao<Cliente> implements ClienteDao {
    
    public ClienteDaoImpl() {
        super(Cliente.class);
    }

    @Override
    public Cliente obterPorEmail(String email) throws ExcecaoDao {
        Cliente retorno = null;
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + Cliente.class.getName() + " x"
                + " where x.email = :email";
        
        try {
            manager.getTransaction().begin();
            TypedQuery<Cliente> query = manager.createQuery(jql, Cliente.class);
            query.setParameter("email", email);
            retorno = query.getSingleResult();
            manager.getTransaction().commit();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
        }
        
        return retorno;
    }
}
