/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.funcionarios;

import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import br.ugf.alfabeta.modelo.funcionarios.FuncionarioDloImpl;
import br.ugf.alfabeta.web.entidades.JsfConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author flavio
 */
@FacesConverter(forClass=Funcionario.class)
@Named(value="funcionarioConverter")
public class FuncionarioConverter extends JsfConverter<Funcionario> {

    @Override
    public Dlo<Funcionario> getDlo() {
        return new FuncionarioDloImpl();
    }
}
