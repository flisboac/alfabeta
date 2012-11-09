/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.editoras.Editora;
import br.ugf.alfabeta.modelo.entidades.Entidade;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import br.ugf.alfabeta.modelo.validacoes.Identidade;
import br.ugf.alfabeta.modelo.validacoes.Identificacao;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ana
 */
@Entity
@Table(name="encomenda", uniqueConstraints={
    @UniqueConstraint(columnNames={"id_encomenda"}),
    @UniqueConstraint(columnNames={"cod_encomenda"})
})
public class Encomenda implements Entidade, Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "id_encomenda", nullable = false, unique = true)
    @NotNull(message = "ID não pode ser nulo.", groups = Identificacao.class)
    private Long id;
    
    @Column(name = "cod_encomenda", nullable = false, unique = true, length = 30)
    @NotNull(message = "Código da encomenda não pode ser nulo.", groups = Identidade.class)
    @Size(min = 1, max = 30, message = "Código da encomenda excede os limites de tamanho.", groups = Identidade.class)
    private String codigo;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "dtcriacao_encomenda", nullable = false)
    @Future(message="Encomenda deve ser gerada em uma data futura.", groups=Identidade.class)
    @NotNull(message = "Encomenda deve ter uma data de criação.", groups = Identidade.class)
    private Date dataHoraCriacao = new Date();
    
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "dtfinalizacao_encomenda")
    @Future(message="Cancelamento da encomenda deve ocorrer em uma data futura.", groups=Identidade.class)
    private Date dataHoraFinalizacao;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name="estado_encomenda", nullable=false)
    @NotNull(message="Encomenda deve possuir um estado.", groups=Identidade.class)
    private EstadoEncomenda estado = EstadoEncomenda.Criada;
    
    @ManyToOne
    @JoinColumn(name="id_funcionario", referencedColumnName="id_cliente", nullable=false)
    @NotNull(message="Todo pedido deve ser originado de um funcionário.", groups=Identidade.class)
    private Funcionario funcionarioCriador;
    
    @ManyToOne
    @JoinColumn(name="idcancelador_funcionario", referencedColumnName="id_cliente")
    private Funcionario funcionarioFinalizador;
    
    @ManyToOne
    @JoinColumn(name="id_editora", referencedColumnName="id_editora", nullable=false)
    private Editora editora;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Date getDataHoraFinalizacao() {
        return dataHoraFinalizacao;
    }

    public void setDataHoraFinalizacao(Date dataHoraFinalizacao) {
        this.dataHoraFinalizacao = dataHoraFinalizacao;
    }

    public EstadoEncomenda getEstado() {
        return estado;
    }

    public void setEstado(EstadoEncomenda estado) {
        this.estado = estado;
    }

    public Funcionario getFuncionarioCriador() {
        return funcionarioCriador;
    }

    public void setFuncionarioCriador(Funcionario funcionarioCriador) {
        this.funcionarioCriador = funcionarioCriador;
    }

    public Funcionario getFuncionarioFinalizador() {
        return funcionarioFinalizador;
    }

    public void setFuncionarioFinalizador(Funcionario funcionarioFinalizador) {
        this.funcionarioFinalizador = funcionarioFinalizador;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final Encomenda other = (Encomenda) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Encomenda{" 
                + "id=" + id 
                + ", codigo=" + codigo 
                + ", dataHoraCriacao=" + dataHoraCriacao 
                + ", dataHoraFinalizacao=" + dataHoraFinalizacao 
                + ", estado=" + estado 
                + ", funcionarioCriador=" + funcionarioCriador 
                + ", funcionarioFinalizador=" + funcionarioFinalizador 
                + ", editora=" + editora 
                + '}';
    }
    
    @Override
    public Encomenda clone() {
        
        Encomenda encomenda = new Encomenda();
        encomenda.id = this.id;
        encomenda.codigo = this.codigo;
        encomenda.dataHoraCriacao = this.dataHoraCriacao;
        encomenda.dataHoraFinalizacao = this.dataHoraFinalizacao;
        encomenda.editora = this.editora;
        encomenda.estado = this.estado;
        encomenda.funcionarioCriador = this.funcionarioCriador;
        encomenda.funcionarioFinalizador = this.funcionarioFinalizador;
        return encomenda;
    }
}
