/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.Validador;
import br.ugf.alfabeta.modelo.entidades.ValidadorEntidade;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author flavio
 */
public class ValidadorCliente implements Validador<Cliente> {
    private Validador<Cliente> validador = new ValidadorEntidade<Cliente>(Cliente.class);
    
    @Override
    public void validarParaConsulta(Cliente cliente) throws ExcecaoDlo {
        this.validador.validarParaConsulta(cliente);
    }
    
    @Override
    public void validarParaPersistencia(Cliente cliente) throws ExcecaoDlo {
        this.validador.validarParaPersistencia(cliente);
    }

    @Override
    public void validarId(Long id) throws ExcecaoDlo {
        this.validador.validarId(id);
    }

    @Override
    public void validarIdentidade(Cliente cliente) throws ExcecaoDlo {
        this.validador.validarIdentidade(cliente);
        
        if (!cliente.getEmail().matches("\\w+@\\w+(\\.\\w+)*")) {
            throw new ExcecaoCriticaDlo("Formato de e-mail inválido.");
        }
        
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new ExcecaoCriticaDlo("Nome do cliente não pode ser nulo.");
        }
    }
    
}
