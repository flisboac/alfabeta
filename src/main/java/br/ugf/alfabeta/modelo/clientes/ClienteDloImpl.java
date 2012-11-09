/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

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
    
    @Override
    public void inserir(Cliente cliente) throws ExcecaoDlo {
        
        super.inserir(cliente);
    }
    
    public static void main(String[] args) {
        
        ClienteDlo clienteDlo = new ClienteDloImpl();
        System.out.println("BonSuccess!");
    }
}
