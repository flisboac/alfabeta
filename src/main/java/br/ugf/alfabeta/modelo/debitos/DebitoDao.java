/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.debitos;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.entidades.Dao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import java.util.List;

/**
 *
 * @author Ana
 */
public interface DebitoDao extends Dao<Debito> {

    public List<Debito> listarPorCliente(Cliente cliente) throws ExcecaoDao;
}
