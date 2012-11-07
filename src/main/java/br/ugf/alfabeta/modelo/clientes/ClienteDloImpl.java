/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;

/**
 *
 * @author Ana
 */
public class ClienteDloImpl extends EntidadeDloPersistencia<Cliente> implements ClienteDlo {
    
    public ClienteDloImpl() {
        super(new ClienteDaoImpl());
    }
    
    public ClienteDloImpl(ClienteDao dao) {
        super(dao);
    }
}
