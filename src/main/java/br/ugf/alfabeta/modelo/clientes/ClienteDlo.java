/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.DloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author flavio
 */
public interface ClienteDlo extends DloPersistencia<Cliente> {

    public Cliente obterPorEmail(String email) throws ExcecaoDlo;
    
}
