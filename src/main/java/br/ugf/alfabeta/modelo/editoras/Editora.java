/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.editoras;

import br.ugf.alfabeta.modelo.entidades.Entidade;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.validacoes.Identidade;
import br.ugf.alfabeta.modelo.validacoes.Identificacao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ana
 */
@Entity
@Table(name = "editora", uniqueConstraints = {
    @UniqueConstraint(name="editora_pk", columnNames = "id_editora"),
    @UniqueConstraint(name="editora_uq", columnNames = "cod_editora")
})
@GroupSequence({Identidade.class, Editora.class})
public class Editora implements Serializable, Entidade {

    @Id
    @GeneratedValue
    @Column(name = "id_editora", nullable = false, unique = true)
    @NotNull(message = "ID não pode ser nulo.", groups = Identificacao.class)
    private Long id;
    
    @Column(name = "nome_editora", nullable = false, unique = true, length = 30)
    @Size(min = 1, max = 30, message = "Nome excede os limites de tamanho.", groups = Identidade.class)
    private String nome;
    
    @Column(name="cod_editora", nullable=false, unique=true, length=30)
    @NotNull(message="Código não pode ser nulo.", groups=Identidade.class)
    @Size(min=1, max=30, message="Código excede os limites de tamanho.", groups=Identidade.class)
    private String codigo;
    
    @Column(name = "end_editora", length = 30)
    @Size(min = 1, max = 30, message = "Endereço ultrapassa os limites de tamanho.", groups = Identidade.class)
    private String endereco;
    
    @OneToMany(mappedBy="editora", cascade = CascadeType.ALL)
    private List<Livro> livros;
    
    
    // [ GETTERS / SETTERS ] ===================================================
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
    
    // [ EQUALS / HASHCODE ] =================================================

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final Editora other = (Editora) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }
    
    // [ TOSTRING ] ============================================================

    @Override
    public String toString() {
        return "Editora{" 
                + "id=" + id 
                + ", nome=" + nome 
                + ", codigo=" + codigo 
                + ", endereco=" + endereco 
                + '}';
    }
    
    @Override
    public Editora clone() {
        
        Editora editora = new Editora();
        editora.id = this.id;
        editora.nome = this.nome;
        editora.endereco = this.endereco;
        editora.codigo = this.codigo;
        editora.livros = this.livros;
        return editora;
    }
}
