/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.DloAbstrato;
import br.ugf.alfabeta.modelo.entidades.Validador;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;

/**
 *
 * @author Ana
 */
public class ClienteDloImpl extends DloAbstrato<Cliente> implements ClienteDlo {
    
    private Validador<Cliente> validador = new ValidadorCliente();
    private ClienteDao dao;
    
    public ClienteDloImpl() {
        this.dao = new ClienteDaoImpl();
    }
    
    public ClienteDloImpl(ClienteDao dao) {
        this.dao = dao;
    }
    
    @Override
    public ClienteDao getDao() {
        return this.dao;
    }
    
    @Override
    protected Validador<Cliente> getValidador() {
        return this.validador;
    }
    
    @Override
    public void inserir(Cliente cliente) throws ExcecaoDlo {
        ClienteDao clienteDao = getDao();
        
        getValidador().validarParaPersistencia(cliente);
        try {
            clienteDao.inserir(cliente);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void alterar(Cliente cliente) throws ExcecaoDlo {
        ClienteDao clienteDao = getDao();
        
        getValidador().validarParaPersistencia(cliente);
        try {
            clienteDao.alterar(cliente);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void persistir(Cliente cliente) throws ExcecaoDlo {
        ClienteDao clienteDao = getDao();
        
        getValidador().validarParaPersistencia(cliente);
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
