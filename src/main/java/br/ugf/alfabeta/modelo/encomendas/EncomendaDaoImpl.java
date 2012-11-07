/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.entidades.JpaDao;

/**
 *
 * @author ubuntu
 */
public class EncomendaDaoImpl extends JpaDao<Encomenda> implements EncomendaDao {
    
    public EncomendaDaoImpl() {
        super(Encomenda.class);
    }
}
