/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author flavio
 */
public interface ClienteDlo extends Dlo<Cliente> {
    
    void inserir(Cliente cliente) throws ExcecaoDlo;
    void alterar(Cliente cliente) throws ExcecaoDlo;
    void persistir(Cliente cliente) throws ExcecaoDlo;
}
