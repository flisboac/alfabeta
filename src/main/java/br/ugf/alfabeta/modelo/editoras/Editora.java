/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.editoras;

import br.ugf.alfabeta.modelo.entidades.Entidade;
import java.io.Serializable;

/**
 *
 * @author Ana
 */
public class Editora implements Serializable, Entidade {
    
    private Integer id;
    private String nome;
    private String endereco;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    
    @Override
    public boolean isIdentidadeValida() {
        
        boolean retorno = (this.nome != null && !this.nome.isEmpty());
        return retorno;
    }
    
    @Override
    public String toString() {
        return "Editora{" 
                + "id=" + id 
                + ", nome=" + nome 
                + ", endereco=" + endereco 
                + '}';
    }
    
    
}
