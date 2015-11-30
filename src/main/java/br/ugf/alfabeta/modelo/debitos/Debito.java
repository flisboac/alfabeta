/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.debitos;

import br.ugf.alfabeta.modelo.entidades.Entidade;
import br.ugf.alfabeta.modelo.pedidos.Pedido;
import br.ugf.alfabeta.modelo.validacoes.Identidade;
import br.ugf.alfabeta.modelo.validacoes.Identificacao;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ana
 */
@Entity
@Table(name = "debito", uniqueConstraints = {
    @UniqueConstraint(name = "debito_pk", columnNames = {"id_debito"}),
    @UniqueConstraint(name = "debito_uq", columnNames = {"id_pedido"})
})
@GroupSequence({Identidade.class, Debito.class})
public class Debito implements Entidade<Debito> {

    @Id
    @GeneratedValue
    @NotNull(message = "ID nao pode ser nulo.", groups = Identificacao.class)
    @Column(name = "id_debito", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "nf_debito", length = 64)
    @Size(min=1, max=64, message="Código da nota fiscal excede os limites de tamanho.", groups=Identidade.class)
    private String codigoNf;
    
    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="id_pedido", referencedColumnName="id_pedido", nullable = false)
    @NotNull(message="Todo débito deve ter um pedido de origem.", groups=Identidade.class)
    @Valid
    private Pedido pedido;
    
    @Column(name="valor_debito", nullable = false)
    @NotNull(message="Débito deve possuir um valor.", groups=Identidade.class)
    private BigDecimal valor = new BigDecimal(0.0);
    
    @Column(name="dtpag_debito", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoNf() {
        return codigoNf;
    }

    public void setCodigoNf(String codigoNf) {
        this.codigoNf = codigoNf;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.pedido != null ? this.pedido.hashCode() : 0);
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
        final Debito other = (Debito) obj;
        if (this.pedido != other.pedido && (this.pedido == null || !this.pedido.equals(other.pedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Debito{" 
                + "id=" + id 
                + ", codigoNf=" + codigoNf 
                + ", pedido=" + pedido 
                + ", valor=" + valor 
                + '}';
    }
    
    @Override
    public Debito clone() {
        
        Debito debito = new Debito();
        return clone(debito);
    }
    
    @Override
    public Debito clone(Debito debito) {
        
        debito.id = this.id;
        debito.pedido = this.pedido;
        debito.valor = this.valor;
        debito.codigoNf = this.codigoNf;
        return debito;
    }

    public boolean isPago() {
        return getCodigoNf() != null;
    }
}