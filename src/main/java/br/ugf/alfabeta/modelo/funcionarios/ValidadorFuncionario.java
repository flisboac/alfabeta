/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.clientes.ValidadorCliente;
import br.ugf.alfabeta.modelo.entidades.Validador;
import br.ugf.alfabeta.modelo.entidades.ValidadorEntidade;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;

/**
 *
 * @author flavio
 */
public class ValidadorFuncionario extends ValidadorEntidade<Funcionario> implements Validador<Funcionario> {
    private Validador<Cliente> validadorCliente = new ValidadorCliente();
    
    @Override
    public Class<Funcionario> getClasseEntidade() {
        return Funcionario.class;
    }
    
    @Override
    public void validarIdentidade(Funcionario funcionario) throws ExcecaoDlo {
        this.validadorCliente.validarIdentidade(funcionario);
        
        if (!funcionario.getMatricula().matches("\\d+")) {
            throw new ExcecaoCriticaDlo("Matrícula com formato inválido.");
        }
    }
    
    @Override
    public void validarId(Long id) throws ExcecaoDlo {
        this.validadorCliente.validarId(id);
    }
    
    @Override
    public void validarParaConsulta(Funcionario funcionario) throws ExcecaoDlo {
        this.validadorCliente.validarParaConsulta(funcionario);
    }
    
    @Override
    public void validarParaPersistencia(Funcionario funcionario) throws ExcecaoDlo {
        this.validadorCliente.validarParaPersistencia(funcionario);
    }
}
