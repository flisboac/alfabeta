/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;

/**
 *
 * @author ubuntu
 */
public class EncomendaDloImpl extends EntidadeDloPersistencia<Encomenda> implements EncomendaDlo {
    
    public EncomendaDloImpl() {
        super(new EncomendaDaoImpl());
    }
    
    public EncomendaDloImpl(EncomendaDao dao) {
        super(dao);
    }
}
