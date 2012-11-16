/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.encomendas;

/**
 *
 * @author Ana
 */
public enum EstadoEncomenda {
    
    // Estado inicial.
    Criado(false, "Criado"),
    // Estado atribuído às encomendas entregues e corretas.
    Finalizado(true, "Finalizado"),
    // Estado atribuído às encomendas que não estão de acordo com o solicitado.
    Cancelado(true, "Cancelado");
    
    private boolean terminal;
    private String titulo;
    
    private EstadoEncomenda(boolean terminal, String titulo) {
        this.terminal = terminal;
        this.titulo = titulo;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public String getTitulo() {
        return titulo;
    }
}
