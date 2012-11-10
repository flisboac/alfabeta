/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    @Override
    public Cliente obterPorEmail(String email) throws ExcecaoDlo {
        
        if (email == null || email.isEmpty()) {
            throw new ExcecaoCriticaDlo("E-mail não pode ser nulo ou vazio.");
        }
        
        ClienteDao dao = (ClienteDao)getDao();
        Cliente retorno = null;
        try {
            retorno = dao.obterPorEmail(email);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(
                    "Erro ao carregar cliente com e-mail '" + email + "'.", ex);
        }
        
        return retorno;
    }
}
