/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.clientes;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.clientes.ClienteDlo;
import br.ugf.alfabeta.modelo.clientes.ClienteDloImpl;
import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.web.entidades.JsfConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author flavio
 */
@FacesConverter(forClass=Cliente.class)
@Named(value="clienteConverter")
public class ClienteConverter extends JsfConverter<Cliente> {
    
    private ClienteDlo clienteDlo = new ClienteDloImpl();
    
    @Override
    public Dlo<Cliente> getDlo() {
        return clienteDlo;
    }
}
