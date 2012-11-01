/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.validacoes.Identidade;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author flavio
 */
@Entity
@Table(name="funcionario")
public class Funcionario extends Cliente {
    
    @Column(name="matr_funcionario", length=30, unique=true, nullable=false) 
    @NotNull(message="Matrícula deve ser fornecida.", groups=Identidade.class)
    @Size(min=1, max=30, message="Matrícula excede os limites de tamanho.", groups=Identidade.class)
    protected String matricula;

    
    // [ GETTERS / SETTERS ] ===================================================
    
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    
    // [ EQUALS / HASHCODE ] ===================================================

    @Override
    public int hashCode() {
        int hash = super.hashCode(); // <<< SUPER >>>
        hash = 89 * hash + (this.matricula != null ? this.matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {             // <<< SUPER >>>
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        if ((this.matricula == null) ? (other.matricula != null) : !this.matricula.equals(other.matricula)) {
            return false;
        }
        return true;
    }
    
    @Override
    public boolean isIdentidadeValida() {
        boolean retorno = (super.isIdentidadeValida())
                && (this.matricula != null && !this.matricula.isEmpty());
        return retorno;
    }
    
    
    // [ TOSTRING ] ============================================================

    @Override
    public String toString() {
        return "Funcionario{" 
                + "<cliente>=" + super.toString()
                + ", matricula=" + matricula 
                + '}';
    }
    
}
