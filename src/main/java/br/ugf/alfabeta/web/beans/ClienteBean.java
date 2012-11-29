/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.ClienteDlo;
import br.ugf.alfabeta.modelo.clientes.ClienteDloImpl;
import br.ugf.alfabeta.modelo.entidades.OrdemListagem;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.livros.LivroDlo;
import br.ugf.alfabeta.modelo.livros.LivroDloImpl;
import br.ugf.alfabeta.modelo.pedidos.ItemPedido;
import br.ugf.alfabeta.modelo.pedidos.ItemPedidoDlo;
import br.ugf.alfabeta.modelo.pedidos.ItemPedidoDloImpl;
import br.ugf.alfabeta.modelo.pedidos.Pedido;
import br.ugf.alfabeta.modelo.pedidos.PedidoDlo;
import br.ugf.alfabeta.modelo.pedidos.PedidoDloImpl;
import br.ugf.alfabeta.web.util.Bean;
import br.ugf.alfabeta.web.util.CampoParaPesquisa;
import br.ugf.alfabeta.web.util.Prefixos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ubuntu
 */
@ManagedBean
@SessionScoped
public class ClienteBean extends Bean {

    private transient ClienteDlo clienteDlo = new ClienteDloImpl();
    private transient LivroDlo livroDlo = new LivroDloImpl();
    private transient PedidoDlo pedidoDlo = new PedidoDloImpl();
    private boolean resultadosInvertidosParaPesquisa;
    private List<CampoParaPesquisa> camposDisponiveisParaPesquisa;
    private String nomeLivroParaPesquisa;
    private List<Livro> livrosParaPesquisa;
    private List<ItemPedido> itensParaPesquisa;
    // Carrinho
    private Pedido pedido;

    @Override
    public void inicializar() {
        super.inicializar();

        this.camposDisponiveisParaPesquisa = Arrays.asList(CampoParaPesquisa.values());
        esvaziar();
        atualizarPesquisa();
    }

    public String getValorTotalFormatado() {
        
        String retorno = "(Vazio)";
        BigDecimal valor = this.pedido.getValorTotal();
        
        if (valor.compareTo(new BigDecimal(0.0)) > 0) {
            retorno = "R$ " + valor;
        }
        
        return retorno;
    }

    public void comprar() {

        Date dataCriacao = new Date();
        this.pedido.setClienteCriador(getHelper().getClienteLogado());
        this.pedido.setDataHoraCriacao(dataCriacao);
        this.pedido.setCodigo(Prefixos.Pedido + String.format("%016X", dataCriacao.getTime()));
        
        try {
            pedidoDlo.efetuarPedido(pedido);
            
            esvaziar();

        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao efetuar pedido!");
        }
    }

    public void esvaziar() {

        this.pedido = new Pedido();
        this.pedido.setItens(new ArrayList<ItemPedido>());
        
        atualizarPesquisa();
        
        if (itensParaPesquisa != null) {
            for (ItemPedido itemParaPesquisa : itensParaPesquisa) {

                itemParaPesquisa.setQuantidade(0);
            }
        }
        
        getHelper().ok("Operação concluída com sucesso.");
    }

    public void atualizarPesquisa() {

        // Ordenação
        String[] nomesParaPesquisa = new String[camposDisponiveisParaPesquisa.size()];
        for (int i = 0; i < nomesParaPesquisa.length; i++) {
            nomesParaPesquisa[i] = camposDisponiveisParaPesquisa.get(i).getNomeCampo();
        }

        OrdemListagem ordem = OrdemListagem.Ascendente;
        if (this.resultadosInvertidosParaPesquisa) {
            ordem = OrdemListagem.Descendente;
        }

        try {
            this.livrosParaPesquisa = this.livroDlo.listarOrdenado(ordem, nomesParaPesquisa);

        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao listar livros", "Por favor, tente novamente!");
            return;
        }

        // Remover livros com estoque baixo, pesquisa por nome
        Iterator<Livro> iter = this.livrosParaPesquisa.iterator();

        while (iter.hasNext()) {
            Livro livro = iter.next();

            if (livro.isForaDeEstoque()
                    || (nomeLivroParaPesquisa != null
                    && !nomeLivroParaPesquisa.isEmpty()
                    && !livro.getNome().contains(nomeLivroParaPesquisa))) {
                iter.remove();
            }
        }

        // Construção dos itens de pedido
        this.itensParaPesquisa = new ArrayList<ItemPedido>();
        for (Livro livro : livrosParaPesquisa) {

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setLivro(livro);

            if (!this.pedido.getItens().isEmpty()) {
                for (ItemPedido itemPedidoComprado : this.pedido.getItens()) {

                    if (itemPedidoComprado.equals(itemPedido)) {
                        itensParaPesquisa.add(itemPedidoComprado);

                    } else {
                        itensParaPesquisa.add(itemPedido);
                    }
                }

            } else {
                itensParaPesquisa.add(itemPedido);
            }
        }

        //getHelper().ok("Listando livros...");
    }

    public void processarItem(ItemPedido itemPedido) {

        if (this.pedido.getItens().contains(itemPedido)) {
            // Remover
            pedido.getItens().remove(itemPedido);
            itemPedido.setQuantidade(0);

        } else {
            // Adicionar
            if (itemPedido.getQuantidade() <= 0) {
                itemPedido.setQuantidade(1);
            }
            pedido.getItens().add(itemPedido);
        }
    }

    public boolean isItemLivroEmEstoque(ItemPedido item) {

        boolean retorno = false;
        int quantidade = getQuantidadeLivrosEmEstoque(item);
        
        if (quantidade >= item.getQuantidade()) {
            retorno = true;
        }
        
        return retorno;
    }
    
    public int getQuantidadeLivrosEmEstoque(ItemPedido itemPedido) {
        
        int retorno = 0;
        
        try {
            retorno = livroDlo.getQuantidadeEmEstoque(itemPedido.getLivro());
            
        } catch (ExcecaoDlo ex) {
            // ...
        }
        
        return retorno;
    }
    
    public List<ItemPedido> getItensPendentes() {
        
        List<ItemPedido> retorno = new ArrayList<ItemPedido>();
        
        for (ItemPedido itemPedido : pedido.getItens()) {
            
            if (!isItemLivroEmEstoque(itemPedido)) {
                retorno.add(itemPedido);
            }
        }
        
        return retorno;
    }
    
    public List<CampoParaPesquisa> getCamposDisponiveisParaPesquisa() {
        return camposDisponiveisParaPesquisa;
    }

    public void setCamposDisponiveisParaPesquisa(List<CampoParaPesquisa> camposDisponiveisParaPesquisa) {
        this.camposDisponiveisParaPesquisa = camposDisponiveisParaPesquisa;
    }

    public boolean isResultadosInvertidosParaPesquisa() {
        return resultadosInvertidosParaPesquisa;
    }

    public void setResultadosInvertidosParaPesquisa(boolean resultadosInvertidosParaPesquisa) {
        this.resultadosInvertidosParaPesquisa = resultadosInvertidosParaPesquisa;
    }

    public String getNomeLivroParaPesquisa() {
        return nomeLivroParaPesquisa;
    }

    public void setNomeLivroParaPesquisa(String nomeLivroParaPesquisa) {
        this.nomeLivroParaPesquisa = nomeLivroParaPesquisa;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Livro> getLivrosParaPesquisa() {
        return livrosParaPesquisa;
    }

    public void setLivrosParaPesquisa(List<Livro> livros) {
        this.livrosParaPesquisa = livros;
    }

    public List<ItemPedido> getItensParaPesquisa() {
        return itensParaPesquisa;
    }

    public void setItensParaPesquisa(List<ItemPedido> itensParaPesquisa) {
        this.itensParaPesquisa = itensParaPesquisa;
    }
}
