/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.clientes.ClienteDaoImpl;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import javax.persistence.EntityManager;
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
    public Funcionario obterPorMatricula(String matricula) throws ExcecaoDao {
        Funcionario retorno = null;
        Class<Funcionario> classeEntidade = getClasseEntidade();
        EntityManager manager = helper.getEntityManager();
        String jql = "select x"
                + " from " + classeEntidade.getName() + " x"
                + " where x.matricula = :matricula";
        
        try {
            manager.getTransaction().begin();
            TypedQuery<Funcionario> query = manager.createQuery(jql, classeEntidade);
            query.setParameter("matricula", matricula);
            retorno = query.getSingleResult();
            manager.getTransaction().commit();
            
        } catch (Exception e) {
            throw new ExcecaoDao(e);
        }
        
        return retorno;
    }
}
