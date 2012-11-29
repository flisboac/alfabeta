/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.debitos;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.validacoes.Persistencia;
import br.ugf.alfabeta.web.util.Prefixos;
import java.util.Date;

/**
 *
 * @author Ana
 */
public class DebitoDloImpl extends EntidadeDloPersistencia<Debito> implements DebitoDlo { 
    
    public DebitoDloImpl() {
        super(new DebitoDaoImpl(), new ValidadorDebito());
    }
    
    public DebitoDloImpl(DebitoDao dao) {
        super(dao, new ValidadorDebito());
    }

    @Override
    public void pagarDebito(Debito debito) throws ExcecaoDlo {
        
        validar(debito, Persistencia.class);
        
        if (debito.getCodigoNf() != null) {
            throw new ExcecaoCriticaDlo("Tentando pagar um debito já pago.");
        }
        
        debito.setCodigoNf(Prefixos.Debito + String.format("%032x", new Date().getTime()));
        
        alterar(debito);
    }
    
}
