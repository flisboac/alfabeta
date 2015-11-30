/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.livros.LivroDlo;
import br.ugf.alfabeta.modelo.livros.LivroDloImpl;
import br.ugf.alfabeta.modelo.pedidos.ItemPedido;
import br.ugf.alfabeta.modelo.pedidos.ItemPedidoDlo;
import br.ugf.alfabeta.modelo.pedidos.ItemPedidoDloImpl;
import br.ugf.alfabeta.modelo.pedidos.Pedido;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ubuntu
 */
public class EncomendaDloImpl extends EntidadeDloPersistencia<Encomenda> implements EncomendaDlo {
    
    protected ItemEncomendaDlo itemEncomendaDlo;
    
    protected LivroDlo livroDlo = new LivroDloImpl();
    
    protected ItemPedidoDlo itemPedidoDlo = new ItemPedidoDloImpl();
    
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

    @Override
    public void finalizar(Encomenda encomenda) throws ExcecaoDlo {
        // TODO Validação
        
        encomenda.setDataHoraFinalizacao(new Date());
        encomenda.setEstado(EstadoEncomenda.Finalizado);

        for (ItemEncomenda itemEncomenda : encomenda.getItens()) {
            Livro livro = itemEncomenda.getLivro();
            livro.setQuantidade(livro.getQuantidade() + itemEncomenda.getQuantidade());

            List<ItemPedido> itensPedidoPendentes = itemPedidoDlo.listarPorLivro(livro);
            for (ItemPedido itemPedidoPendente : itensPedidoPendentes) {
                Pedido pedido = itemPedidoPendente.getPedido();
                if (itemPedidoPendente.isPendente() && !pedido.getEstado().isTerminal()) {
                    livro.setQuantidade(livro.getQuantidade() - itemPedidoPendente.getQuantidade());
                    itemPedidoPendente.setPendente(false);
                    itemPedidoDlo.persistir(itemPedidoPendente);
                }
            }

            livroDlo.persistir(livro);
        }
        
        persistir(encomenda);
    }
    
}
