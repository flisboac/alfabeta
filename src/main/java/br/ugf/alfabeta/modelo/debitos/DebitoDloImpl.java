/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.debitos;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.clientes.ValidadorCliente;
import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.entidades.Validador;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;
import br.ugf.alfabeta.modelo.validacoes.Consulta;
import br.ugf.alfabeta.modelo.validacoes.Persistencia;
import br.ugf.alfabeta.web.util.Prefixos;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class DebitoDloImpl extends EntidadeDloPersistencia<Debito> implements DebitoDlo { 
    
    private transient Validador<Cliente> validadorCliente = new ValidadorCliente();
    
    public DebitoDloImpl() {
        super(new DebitoDaoImpl(), new ValidadorDebito());
    }
    
    public DebitoDloImpl(DebitoDao dao) {
        super(dao, new ValidadorDebito());
    }

    @Override
    public List<Debito> listarPorCliente(Cliente cliente) throws ExcecaoDlo {
        
        List<Debito> retorno = null;
        DebitoDao debitoDao = (DebitoDao) getDao();
        
        validadorCliente.validar(cliente, Consulta.class);
        
        try {
            retorno = debitoDao.listarPorCliente(cliente);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex);
        }
        
        if (retorno == null) {
            throw new ExcecaoPersistenciaDlo("Valor nulo retornado.");
        }
        
        return retorno;
    }
    
    @Override
    public void pagarDebito(Debito debito) throws ExcecaoDlo {
        
        validar(debito, Persistencia.class);
        
        if (debito.getCodigoNf() != null) {
            throw new ExcecaoCriticaDlo("Tentando pagar um debito já pago.");
        }
        
        debito.setCodigoNf(Prefixos.Debito + String.format("%016x", new Date().getTime()));
        debito.setDataPagamento(new Date());
        alterar(debito);
    }
    
}
