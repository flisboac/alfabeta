/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.entidades.Validador;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.livros.ValidadorLivro;
import br.ugf.alfabeta.modelo.validacoes.Persistencia;
import java.util.List;

/**
 *
 * @author flavio
 */
public class ItemEncomendaDloImpl extends EntidadeDloPersistencia<ItemEncomenda> implements ItemEncomendaDlo {
    
    protected Validador<Encomenda> validadorEncomenda = new ValidadorEncomenda();
    protected Validador<Livro> validadorLivro = new ValidadorLivro();
    
    public ItemEncomendaDloImpl() {
        super(new ItemEncomendaDaoImpl(), new ValidadorItemEncomenda());
    }
    
    public ItemEncomendaDloImpl(ItemEncomendaDao dao) {
        super(dao, new ValidadorItemEncomenda());
    }

    protected Validador<Encomenda> getValidadorEncomenda() {
        return this.validadorEncomenda;
    }
    
    protected Validador<Livro> getValidadorLivro() {
        return this.validadorLivro;
    }
    
    @Override
    public List<ItemEncomenda> listarPorEncomenda(Encomenda encomenda) throws ExcecaoDlo {
        List<ItemEncomenda> retorno = null;
        ItemEncomendaDao itemEncomendaDao = (ItemEncomendaDao) getDao();
        Validador<Encomenda> _validadorEncomenda = getValidadorEncomenda();
        
        _validadorEncomenda.validar(encomenda, Persistencia.class);
        try {
            retorno = itemEncomendaDao.listarPorEncomenda(encomenda);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex);
        }
        
        if (retorno == null) {
            throw new ExcecaoPersistenciaDlo("Valor nulo retornado.");
        }
        
        return retorno;
    }

    @Override
    public List<ItemEncomenda> listarPorLivro(Livro livro) throws ExcecaoDlo {
        List<ItemEncomenda> retorno = null;
        ItemEncomendaDao itemEncomendaDao = (ItemEncomendaDao) getDao();
        Validador<Livro> _validadorLivro = getValidadorLivro();
        
        _validadorLivro.validar(livro, Persistencia.class);
        try {
            retorno = itemEncomendaDao.listarPorLivro(livro);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex);
        }
        
        if (retorno == null) {
            throw new ExcecaoPersistenciaDlo("Valor nulo retornado.");
        }
        
        return retorno;
    }
    
}
