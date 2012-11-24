/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ana
 */
public class JpaHelper {
    
    public static final String NomePersistenceUnit = "alfabeta";
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(NomePersistenceUnit);
    private EntityManagerFactory factory;
    
    public JpaHelper() {
        
    }
    
    public JpaHelper(String nomeUnit, String... opcoes) {
        String unit = nomeUnit;
        
        if (opcoes != null && opcoes.length > 0) {
            String sep = ";";
            
            for (String opcao : opcoes) {                
                unit += sep + opcao;
            }
        }
        
        this.factory = Persistence.createEntityManagerFactory(unit);
    }
    
    public EntityManagerFactory getEntityManagerFactory() {
        EntityManagerFactory retorno = this.factory;
        
        if (retorno == null) {
            retorno = emf;
        }
        
        return retorno;
    }
    
    public EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
    
}
