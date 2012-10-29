/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.Entidade;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Representa um cliente que acessa o portal do cliente.
 * O cliente é a base para {@link Funcionario}.
 * 
 * @author Ana
 */
@Entity
@Table(name="cliente")
@Inheritance(strategy= InheritanceType.JOINED)
public class Cliente implements Serializable, Entidade {
   
    /**
     * Ientificação numérica única para a entidade.
     */
    @Id
    @GeneratedValue
    @Column(name="id_cliente", nullable=false, unique=true)
    protected Long idCliente;
    
    /**
     * E-mail do cliente.
     * O E-mail identifica unicamente o cliente.
     */
    @Column(name="email_cliente", length=30, nullable=false, unique=true)
    protected String email;
    
    /**
     * A senha do cliente, em texto puro.
     */
    @Column(name="senha_cliente", length=30)
    protected String senha;
    
    /**
     * O nome completo do cliente.
     */
    @Column(name="nome_cliente", length=30, nullable=false)
    protected String nome;
    
    
    // [ GETTERS / SETTERS ] ===================================================
    
    
    @Override
    public Long getId() {
        return idCliente;
    }

    public void setId(Long id) {
        this.idCliente = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    // [ IDENTIDADE / EQUALS / HASHCODE ] ======================================

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.email != null ? this.email.hashCode() : 0);
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
        final Cliente other = (Cliente) obj;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }
    
    @Override
    public boolean isIdentidadeValida() {
        
        boolean retorno = (this.email != null && !this.email.isEmpty());
        return retorno;
    }
    
    // [ TOSTRING / UTIL ] =====================================================

    @Override
    public String toString() {
        return "Cliente{" 
                + "id=" + idCliente 
                + ", email=" + email 
                + ", senha=" + senha 
                + ", nome=" + nome 
                + '}';
    }
    
}
