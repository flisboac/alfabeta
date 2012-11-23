/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.clientes.ClienteDaoImpl;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author flavio
 */
public class FuncionarioDaoImpl extends ClienteDaoImpl<Funcionario> implements FuncionarioDao {
    
    public FuncionarioDaoImpl() {
        super(Funcionario.class);
    }

    @Override
    public void atualizar(Funcionario funcionario) throws ExcecaoDao {
        super.atualizar(funcionario);
        
        EntityManager manager = this.helper.getEntityManager();
        
        try {
            manager.refresh(funcionario);
            funcionario.getEncomendas().size();
            
        } catch (PersistenceException e) {
            throw new ExcecaoDao("Erro ao atualizar entidade '" + this.classe.getName() + "'.", e);
            
        } finally {
            manager.close();
        }
    }
    
    @Override
    public boolean existe(Funcionario itemEncomenda) throws ExcecaoDao {
        
        boolean retorno = super.existe(itemEncomenda);
        
        if (!retorno) {
            EntityManager manager = this.helper.getEntityManager();
            
            try {
                String jpql = "select x"
                        + " from " + Funcionario.class.getName() + " x"
                        + " where x.matricula = :matricula";
                TypedQuery<Funcionario> query = manager.createQuery(jpql, Funcionario.class);
                query.setParameter("matricula", itemEncomenda.getMatricula());
                Funcionario entidade = query.getSingleResult();
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
    public Funcionario obterPorMatricula(String matricula) throws ExcecaoDao {
        Funcionario retorno = null;
        Class<Funcionario> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.matricula = :matricula";
        
        try {
            TypedQuery<Funcionario> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("matricula", matricula);
            retorno = query.getSingleResult();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
            
        } finally {
            manager.close();
        }
        
        return retorno;
    }
}
