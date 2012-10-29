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
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("alfabeta");
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
