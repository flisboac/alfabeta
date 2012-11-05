/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.debitos;

import br.ugf.alfabeta.modelo.entidades.Entidade;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Ana
 */
@Entity
@Table(name="debito", uniqueConstraints= {
       @UniqueConstraint(columnNames={"id_debito"}),
        })
public class Debito implements Serializable, Entidade{

    @Id
    private Long id;
    private String nf;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id){
    
        this.id = id;
    }
    
    public String getNf(){
        return nf;
    }
    
}