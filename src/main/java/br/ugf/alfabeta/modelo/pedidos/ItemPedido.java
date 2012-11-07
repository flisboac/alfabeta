/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

import br.ugf.alfabeta.modelo.entidades.Entidade;
import br.ugf.alfabeta.modelo.livros.Livro;
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

/**
 *
 * @author Ana
 */
@Entity
@Table(name="itempedido", uniqueConstraints= {
    @UniqueConstraint(columnNames={"id_itempedido"}),
    @UniqueConstraint(columnNames={"id_pedido", "id_livro"})
})
public class ItemPedido implements Serializable, Entidade {
    
    @Id
    @GeneratedValue
    @Column(name="id_itempedido", nullable=false, unique=true)
    @NotNull(message="ID não pode ser nulo.", groups=Identificacao.class)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="id_pedido", referencedColumnName="id_pedido", nullable=false)
    @NotNull(message="Item de pedido deve pertencer a um pedido.", groups=Identidade.class)
    private Pedido pedido;
    
    @ManyToOne
    @JoinColumn(name="id_livro", referencedColumnName="id_livro", nullable=false)
    @NotNull(message="Item de pedido deve referenciar um livro.", groups=Identidade.class)
    private Livro livro;
    
    @Column(name="qtd_itempedido")
    private int quantidade;
    
    @Column(name="pendente_itempedido", nullable=false)
    private boolean pendente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isPendente() {
        return pendente;
    }

    public void setPendente(boolean pendente) {
        this.pendente = pendente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.pedido != null ? this.pedido.hashCode() : 0);
        hash = 31 * hash + (this.livro != null ? this.livro.hashCode() : 0);
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
        final ItemPedido other = (ItemPedido) obj;
        if (this.pedido != other.pedido && (this.pedido == null || !this.pedido.equals(other.pedido))) {
            return false;
        }
        if (this.livro != other.livro && (this.livro == null || !this.livro.equals(other.livro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemPedido{" 
                + "id=" + id 
                + ", pedido=" + pedido 
                + ", livro=" + livro 
                + ", quantidade=" + quantidade 
                + ", pendente=" + pendente 
                + '}';
    }
}
