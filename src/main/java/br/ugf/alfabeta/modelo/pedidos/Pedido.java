/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.debitos.Debito;
import br.ugf.alfabeta.modelo.entidades.Entidade;
import br.ugf.alfabeta.modelo.validacoes.Identidade;
import br.ugf.alfabeta.modelo.validacoes.Identificacao;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "pedido", uniqueConstraints = {
    @UniqueConstraint(name = "pedido_pk", columnNames = {"id_pedido"}),
    @UniqueConstraint(name = "pedido_uq", columnNames = {"cod_pedido"})
})
public class Pedido implements Serializable, Entidade {

    @Id
    @GeneratedValue
    @Column(name = "id_pedido", nullable = false, unique = true)
    @NotNull(message = "ID não pode ser nulo.", groups = Identificacao.class)
    private Long id;
    
    @Column(name = "cod_pedido", nullable = false, unique = true, length = 30)
    @NotNull(message = "Código do pedido não pode ser nulo.", groups = Identidade.class)
    @Size(min = 1, max = 30, message = "Código do pedido excede os limites de tamanho.", groups = Identidade.class)
    private String codigo;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "dtcriacao_pedido", nullable = false)
    @Future(message="Pedido deve ser gerado em uma data futura.", groups=Identidade.class)
    @NotNull(message = "Pedido deve ter uma data de criação.", groups = Identidade.class)
    private Date dataHoraCriacao = new Date();
    
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "dtcancelamento_pedido")
    @Future(message="Cancelamento do pedido deve ocorrer em uma data futura.", groups=Identidade.class)
    private Date dataHoraCancelamento;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name="estado_encomenda", nullable=false)
    @NotNull(message="Encomenda deve possuir um estado.", groups=Identidade.class)
    private EstadoPedido estado = EstadoPedido.Criado;
    
    @ManyToOne
    @JoinColumn(name="id_cliente", referencedColumnName="id_cliente")
    //@PrimaryKeyJoinColumn
    @NotNull(message="Todo pedido deve ser originado de um cliente.", groups=Identidade.class)
    private Cliente clienteCriador;
    
    @ManyToOne
    @JoinColumn(name="idcancelador_cliente", referencedColumnName="id_cliente")
    //@PrimaryKeyJoinColumn
    private Cliente clienteCancelador;
    
    @OneToMany(mappedBy="pedido", cascade= CascadeType.REMOVE)
    private List<ItemPedido> itens;
    
    @OneToOne(mappedBy="pedido", orphanRemoval = true, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Debito debito;
    
    
    //[ GETTERS / SETTERS ] ====================================================
    
    
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

    public Date getDataHoraCancelamento() {
        return dataHoraCancelamento;
    }

    public void setDataHoraCancelamento(Date dataHoraCancelamento) {
        this.dataHoraCancelamento = dataHoraCancelamento;
    }

    public Cliente getClienteCriador() {
        return clienteCriador;
    }

    public void setClienteCriador(Cliente clienteCriador) {
        this.clienteCriador = clienteCriador;
    }

    public Cliente getClienteCancelador() {
        return clienteCancelador;
    }

    public void setClienteCancelador(Cliente clienteCancelador) {
        this.clienteCancelador = clienteCancelador;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Debito getDebito() {
        return debito;
    }

    public void setDebito(Debito debito) {
        this.debito = debito;
    }

    
    // [ HASHCODE / EQUALS ] ===================================================
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final Pedido other = (Pedido) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    
    // [ UTILS ] ===============================================================
    
    
    @Override
    public String toString() {
        return "Pedido{" 
                + "id=" + id 
                + ", codigo=" + codigo 
                + ", dataHoraCriacao=" + dataHoraCriacao 
                + ", dataHoraCancelamento=" + dataHoraCancelamento 
                + ", clienteCriador=" + clienteCriador 
                + ", clienteCancelador=" + clienteCancelador 
                + '}';
    }
    
    @Override
    public Pedido clone() {
        
        Pedido pedido = new Pedido();
        pedido.id = this.id;
        pedido.codigo = this.codigo;
        pedido.clienteCriador = this.clienteCriador;
        pedido.clienteCancelador = this.clienteCancelador;
        pedido.dataHoraCriacao = this.dataHoraCriacao;
        pedido.dataHoraCancelamento = this.dataHoraCancelamento;
        pedido.itens = this.itens;
        pedido.estado = this.estado;
        pedido.debito = this.debito;
        return pedido;
    }
    
}
