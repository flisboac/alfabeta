/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;

/**
 *
 * @author ubuntu
 */
public class EncomendaDloImpl extends EntidadeDloPersistencia<Encomenda> implements EncomendaDlo {
    
    protected ItemEncomendaDlo itemEncomendaDlo;
    
    public EncomendaDloImpl() {
        super(new EncomendaDaoImpl(), new ValidadorEncomenda());
        this.itemEncomendaDlo = new ItemEncomendaDloImpl();
    }
    
    public EncomendaDloImpl(EncomendaDao dao) {
        super(dao, new ValidadorEncomenda());
        this.itemEncomendaDlo = new ItemEncomendaDloImpl();
    }
    
    public EncomendaDloImpl(ItemEncomendaDlo itemEncomendaDlo) {
        super(new EncomendaDaoImpl(), new ValidadorEncomenda());
        this.itemEncomendaDlo = itemEncomendaDlo;
    }
    
    public EncomendaDloImpl(EncomendaDao dao, ItemEncomendaDlo itemEncomendaDlo) {
        super(dao, new ValidadorEncomenda());
        this.itemEncomendaDlo = itemEncomendaDlo;
    }
    
}
