/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.debitos;

import br.ugf.alfabeta.modelo.entidades.Entidade;
import br.ugf.alfabeta.modelo.pedidos.Pedido;
import br.ugf.alfabeta.modelo.validacoes.Identidade;
import br.ugf.alfabeta.modelo.validacoes.Identificacao;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ana
 */
@Entity
@Table(name = "debito", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_debito"}),
    @UniqueConstraint(columnNames = {"id_pedido"})
})
public class Debito implements Serializable, Entidade {

    @Id
    @GeneratedValue
    @NotNull(message = "ID nao pode ser nulo.", groups = Identificacao.class)
    @Column(name = "id_debito", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "nf_debito", length = 30)
    @Size(min=1, max=30, message="Código da nota fiscal excede os limites de tamanho.", groups=Identidade.class)
    private String codigoNf;
    
    @ManyToOne
    @JoinColumn(name="id_pedido", referencedColumnName="id_pedido", nullable = false)
    @NotNull(message="Todo débito deve ter um pedido de origem.", groups=Identidade.class)
    private Pedido pedido;
    
    @Column(name="valor_debito", nullable = false)
    private double valor;
    
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
    
    
}