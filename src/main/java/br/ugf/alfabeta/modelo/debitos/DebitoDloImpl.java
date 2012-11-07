/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.debitos;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;

/**
 *
 * @author Ana
 */
public class DebitoDloImpl extends EntidadeDloPersistencia<Debito> implements DebitoDlo { 
    
    public DebitoDloImpl() {
        super(new DebitoDaoImpl());
    }
    
    public DebitoDloImpl(DebitoDao dao) {
        super(dao);
    }
}
