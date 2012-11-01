/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.EntidadeDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;
import br.ugf.alfabeta.modelo.validacoes.Persistencia;

/**
 *
 * @author Ana
 */
public class ClienteDloImpl extends EntidadeDlo<Cliente> implements ClienteDlo {
    
    public ClienteDloImpl() {
        super(new ClienteDaoImpl());
    }
    
    public ClienteDloImpl(ClienteDao dao) {
        super(dao);
    }
    
    @Override
    public void inserir(Cliente cliente) throws ExcecaoDlo {
        ClienteDao clienteDao = (ClienteDao) getDao();
        
        validar(cliente, Persistencia.class);
        try {
            clienteDao.inserir(cliente);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void alterar(Cliente cliente) throws ExcecaoDlo {
        ClienteDao clienteDao = (ClienteDao) getDao();
        
        validar(cliente, Persistencia.class);
        try {
            clienteDao.alterar(cliente);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void persistir(Cliente cliente) throws ExcecaoDlo {
        ClienteDao clienteDao = (ClienteDao) getDao();
        
        validar(cliente, Persistencia.class);
        try {
            if (existe(cliente)) {
                clienteDao.alterar(cliente);
            } else {
                clienteDao.inserir(cliente);
            }
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
}
