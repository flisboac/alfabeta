/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.entidades.Entidade;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.validacoes.Identidade;
import br.ugf.alfabeta.modelo.validacoes.Identificacao;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author flavio
 */
@Entity
@Table(name="itemencomenda", uniqueConstraints={
    @UniqueConstraint(name = "itemencomenda_pk", columnNames={"id_itemencomenda"}),
    @UniqueConstraint(name = "itemencomenda_uq", columnNames={"id_encomenda", "id_livro"})
})
@GroupSequence({Identidade.class, ItemEncomenda.class})
public class ItemEncomenda implements Entidade {
    
    @Id
    @GeneratedValue
    @Column(name="id_itemencomenda", unique=true, nullable=false)
    @NotNull(message="ID não pode ser nulo.", groups=Identificacao.class)
    private Long id;
    
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="id_encomenda", nullable=false, referencedColumnName="id_encomenda")
    @NotNull(message="Item de encomenda deve pertencer a uma encomenda.", groups=Identidade.class)
    @Valid
    private Encomenda encomenda;
    
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="id_livro", referencedColumnName="id_livro", nullable=false)
    @NotNull(message="Item de encomenda deve referenciar um livro.", groups=Identidade.class)
    @Valid
    private Livro livro;
    
    @Column(name="qtd_itemencomenda")
    private int quantidade;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.encomenda != null ? this.encomenda.hashCode() : 0);
        hash = 29 * hash + (this.livro != null ? this.livro.hashCode() : 0);
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
        final ItemEncomenda other = (ItemEncomenda) obj;
        if (this.encomenda != other.encomenda && (this.encomenda == null || !this.encomenda.equals(other.encomenda))) {
            return false;
        }
        if (this.livro != other.livro && (this.livro == null || !this.livro.equals(other.livro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemEncomenda{" 
                + "id=" + id 
                + ", encomenda=" + encomenda 
                + ", livro=" + livro 
                + ", quantidade=" + quantidade 
                + '}';
    }
    
    @Override
    public ItemEncomenda clone() {
        
        ItemEncomenda itemEncomenda = new ItemEncomenda();
        itemEncomenda.id = this.id;
        itemEncomenda.livro = this.livro;
        itemEncomenda.encomenda = this.encomenda;
        itemEncomenda.quantidade = this.quantidade;
        return itemEncomenda;
    }
    
}
