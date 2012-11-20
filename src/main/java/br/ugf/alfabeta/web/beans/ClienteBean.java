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
import br.ugf.alfabeta.modelo.pedidos.Pedido;
import br.ugf.alfabeta.modelo.pedidos.PedidoDlo;
import br.ugf.alfabeta.modelo.pedidos.PedidoDloImpl;
import br.ugf.alfabeta.web.util.Bean;
import br.ugf.alfabeta.web.util.CampoParaPesquisa;
import br.ugf.alfabeta.web.util.Prefixos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
        
        return "R$ " + this.pedido.getValorTotal();
    }
    
    public void comprar() {
        
        Date dataCriacao = new Date();
        this.pedido.setClienteCriador(getHelper().getClienteLogado());
        this.pedido.setDataHoraCriacao(dataCriacao);
        this.pedido.setCodigo(Prefixos.Pedido + String.format("%016X", dataCriacao.getTime()));
        
        try {
            pedidoDlo.inserir(pedido);
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao efetuar pedido!");
        }
        
        esvaziar();
    }
    
    public void esvaziar() {
        
        this.pedido = new Pedido();
        this.pedido.setItens(new ArrayList<ItemPedido>());
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
        
        // Pesquisa por nome
        if (nomeLivroParaPesquisa != null && !nomeLivroParaPesquisa.isEmpty()) {
            Iterator<Livro> iter = this.livrosParaPesquisa.iterator();
            
            while (iter.hasNext()) {
                Livro livro = iter.next();
                
                if (!livro.getNome().contains(nomeLivroParaPesquisa)) {
                    iter.remove();
                }
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
            
        } else {
            // Adicionar
            pedido.getItens().add(itemPedido);
        }
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
