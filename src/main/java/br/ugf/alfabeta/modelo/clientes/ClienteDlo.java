/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import java.util.List;

/**
 *
 * @author Ana
 */
public class ClienteDlo implements Dlo<Cliente> {

    @Override
    public Cliente obter(long id) throws ExcecaoDlo {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existe(Cliente entidade) throws ExcecaoDlo {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Cliente> listar() throws ExcecaoDlo {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
