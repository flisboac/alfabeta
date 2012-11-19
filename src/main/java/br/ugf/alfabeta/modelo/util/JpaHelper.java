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
    
    private EntityManagerFactory emf;
    
    public JpaHelper() {
        this.emf = Persistence.createEntityManagerFactory(NomePersistenceUnit);
    }
    
    public JpaHelper(String nomeUnit, String... opcoes) {
        String unit = nomeUnit;
        
        if (opcoes != null && opcoes.length > 0) {
            String sep = ";";
            
            for (String opcao : opcoes) {                
                unit += sep + opcao;
            }
        }
        
        this.emf = Persistence.createEntityManagerFactory(unit);
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
}
