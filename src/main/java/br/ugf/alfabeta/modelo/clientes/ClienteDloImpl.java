/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.entidades.Validador;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;

/**
 *
 * @author Ana
 */
public class ClienteDloImpl<T extends Cliente> extends EntidadeDloPersistencia<T> implements ClienteDlo<T> {
    
    public ClienteDloImpl() {
        super(new ClienteDaoImpl(), new ValidadorCliente());
    }
    
    public ClienteDloImpl(ClienteDao<T> dao) {
        super(dao, new ValidadorCliente());
    }
    
    public ClienteDloImpl(Validador<T> validador) {
        super(new ClienteDaoImpl(), validador);
    }
    
    public ClienteDloImpl(ClienteDao<T> dao, Validador<T> validador) {
        super(dao, validador);
    }
    
    @Override
    public void inserir(T cliente) throws ExcecaoDlo {
        
        super.inserir(cliente);
    }
    
    @Override
    public T obterPorEmail(String email) throws ExcecaoDlo {
        
        if (email == null || email.isEmpty()) {
            throw new ExcecaoCriticaDlo("E-mail não pode ser nulo ou vazio.");
        }
        
        ClienteDao<T> clienteDao = (ClienteDao<T>)getDao();
        T retorno = null;
        try {
            retorno = clienteDao.obterPorEmail(email);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(
                    "Erro ao obter cliente com e-mail '" + email + "'.", ex);
        }
        
        return retorno;
    }
}
