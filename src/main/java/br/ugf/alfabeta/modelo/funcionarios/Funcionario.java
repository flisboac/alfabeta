/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.funcionarios;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.encomendas.Encomenda;
import br.ugf.alfabeta.modelo.validacoes.Identidade;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author flavio
 */
@Entity
@Table(name="funcionario", uniqueConstraints={
    @UniqueConstraint(name = "funcionario_uq", columnNames={"matr_funcionario"})
})
@GroupSequence({Identidade.class, Funcionario.class})
public class Funcionario extends Cliente {
    
    @Column(name="matr_funcionario", length=30, unique=true, nullable=false) 
    @NotNull(message="Matrícula deve ser fornecida.", groups=Identidade.class)
    @Size(min=1, max=30, message="Matrícula excede os limites de tamanho.", groups=Identidade.class)
    protected String matricula;
    
    @OneToMany(mappedBy="funcionarioCriador", cascade = CascadeType.ALL)
    protected List<Encomenda> encomendas;
    
    
    // [ GETTERS / SETTERS ] ===================================================
    
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Encomenda> getEncomendas() {
        return encomendas;
    }

    public void setEncomendas(List<Encomenda> encomendas) {
        this.encomendas = encomendas;
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
    
    // [ TOSTRING ] ============================================================

    @Override
    public String toString() {
        return "Funcionario{" 
                + "<cliente>=" + super.toString()
                + ", matricula=" + matricula 
                + '}';
    }
    
    @Override
    public Funcionario clone() {
        Funcionario funcionario = new Funcionario();
        super.clone(funcionario);
        funcionario.matricula = matricula;
        funcionario.encomendas = this.encomendas;
        return funcionario;
    }
}
