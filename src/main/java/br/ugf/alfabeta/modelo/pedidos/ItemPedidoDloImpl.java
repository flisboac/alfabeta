/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

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
public class ItemPedidoDloImpl extends EntidadeDloPersistencia<ItemPedido> implements ItemPedidoDlo {
    
    protected Validador<Livro> validadorLivro = new ValidadorLivro();
    protected Validador<Pedido> validadorPedido = new ValidadorPedido();
    
    public ItemPedidoDloImpl() {
        super(new ItemPedidoDaoImpl(), new ValidadorItemPedido());
    }
    
    public ItemPedidoDloImpl(ItemPedidoDao dao) {
        super(dao, new ValidadorItemPedido());
    }
    
    public Validador<Livro> getValidadorLivro() {
        return validadorLivro;
    }
    
    public Validador<Pedido> getValidadorPedido() {
        return validadorPedido;
    }
    
    @Override
    public List<ItemPedido> listarPorLivro(Livro livro) throws ExcecaoDlo {
        List<ItemPedido> retorno = null;
        ItemPedidoDao itemPedidoDao = (ItemPedidoDao) getDao();
        
        getValidadorLivro().validar(livro, Persistencia.class);
        
        try {
            itemPedidoDao.listarPorLivro(livro);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex);
        }
        
        if (retorno == null) {
            throw new ExcecaoPersistenciaDlo("Valor nulo retornado.");
        }
        
        return retorno;
    }

    @Override
    public List<ItemPedido> listarPorPedido(Pedido pedido) throws ExcecaoDlo {
        List<ItemPedido> retorno = null;
        ItemPedidoDao itemPedidoDao = (ItemPedidoDao) getDao();
        
        getValidadorPedido().validar(pedido, Persistencia.class);
        
        try {
            retorno = itemPedidoDao.listarPorPedido(pedido);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex);
        }
        
        if (retorno == null) {
            throw new ExcecaoPersistenciaDlo("Valor nulo retornado.");
        }
        
        return retorno;
    }

    @Override
    public List<ItemPedido> listarPendentesAtivos() throws ExcecaoDlo {
        List<ItemPedido> retorno = null;
        ItemPedidoDao itemPedidoDao = (ItemPedidoDao) getDao();
        
        try {
            itemPedidoDao.listarPendentesAtivos();
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex);
        }
        
        if (retorno == null) {
            throw new ExcecaoPersistenciaDlo("Valor nulo retornado.");
        }
        
        return retorno;
    }
}
