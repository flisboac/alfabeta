/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.debitos;

import br.ugf.alfabeta.modelo.entidades.JpaDao;

/**
 *
 * @author Ana
 */
public class DebitoDaoImpl extends JpaDao<Debito> implements DebitoDao {
    
    public DebitoDaoImpl() {
        super(Debito.class);
    }
    
}
