/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.editoras;

import br.ugf.alfabeta.modelo.entidades.Entidade;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Ana
 */
@Entity
@Table(name="editora", uniqueConstraints={
    @UniqueConstraint(columnNames="id_editora"),
    @UniqueConstraint(columnNames="nome_editora")
})
public class Editora implements Serializable, Entidade {
    
    @Id
    @GeneratedValue
    @Column(name="id_editora")
    private Long id;
    
    @Column(name="nome_editora")
    private String nome;
    
    @Column(name="end_editora")
    private String endereco;

    
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
    
    
    // [ EQUALS / HASHCODE ] =================================================
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.nome != null ? this.nome.hashCode() : 0);
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
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
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
                + ", endereco=" + endereco 
                + '}';
    }
    
    
}
