/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.debitos;

import br.ugf.alfabeta.modelo.entidades.DloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author Ana
 */
public interface DebitoDlo extends DloPersistencia<Debito> {
    
    public void pagarDebito(Debito debito) throws ExcecaoDlo;
}
