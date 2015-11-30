/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.entidades.DloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author ubuntu
 */
public interface EncomendaDlo extends DloPersistencia<Encomenda> {
    
    public void finalizar(Encomenda encomenda) throws ExcecaoDlo;
}
