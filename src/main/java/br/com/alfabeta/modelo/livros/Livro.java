/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alfabeta.modelo.livros;

import br.ugf.alfabeta.modelo.editoras.Editora;
import br.ugf.alfabeta.modelo.entidades.Entidade;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Ana
 */
@Entity
@Table(name="livro", uniqueConstraints= {
    @UniqueConstraint(name="id", columnNames={"id_livro"}),
    @UniqueConstraint(name="identidade", columnNames={"cod_livro"})
})
public class Livro implements Serializable, Entidade {
    
    @Id
    @GeneratedValue
    @Column(name="id_livro")
    private Long id; //quando vamos  usar um atributo para chave do BD e mapeamos com @generatedValue ele deve ser tipo Long.
    
    @Column(name="cod_livro")
    private String codigo;
    
    @Column(name="nome_livro")
    private String nome;

    @Column(name="id_editora")
    @ManyToOne
    @JoinColumn(name="id_editora", referencedColumnName="id_editora")
    private Editora editora;
    
    // [ GETTERS / SETTERS ] ===================================================
    
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // [ EQUALS / HASHCODE ] ===================================================
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final Livro other = (Livro) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }
    
    @Override
    public boolean isIdentidadeValida() {
        
        boolean retorno = (this.codigo != null && !this.codigo.isEmpty());
        return retorno;
    }
    
    // [ TOSTRING ] ============================================================

    @Override
    public String toString() {
        return "Livro{" 
                + "id=" + id 
                + ", codigo=" + codigo 
                + ", nome=" + nome 
                + '}';
    }
    
}
