/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.pedidos;

/**
 *
 * @author Ana
 */
public enum EstadoPedido {
    
    // Estado inicial do pedido.
    Criado(false),
    // Estado atribuído a um pedido já atendido.
    Atendido(true),
    // Estado atribuído a um pedido cancelado por um cliente.
    Cancelado(true);
    
    private boolean terminal;
    
    private EstadoPedido(boolean terminal) {
        this.terminal = terminal;
    }

    public boolean isTerminal() {
        return terminal;
    }
}
