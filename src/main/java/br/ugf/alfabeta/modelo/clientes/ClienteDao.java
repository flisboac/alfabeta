/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.clientes;

import br.ugf.alfabeta.modelo.entidades.Dao;
import br.ugf.alfabeta.modelo.entidades.JpaDao;
import java.util.List;

/**
 *
 * @author Ana
 */
public class ClienteDao implements Dao<Cliente> {
    private JpaDao<Cliente> dao = new JpaDao<Cliente>(Cliente.class);

    @Override
    public Cliente obter(long id) {
        return this.dao.obter(id);
    }

    @Override
    public boolean existe(Cliente entidade) {
        return this.dao.excluir(entidade);
    }

    @Override
    public List<Cliente> listar() {
        return this.listar();
    }
    
    public boolean inserir(Cliente cliente) {
        return this.dao.inserir(cliente);
    }
    
    public boolean alterar(Cliente cliente) {
        return this.dao.alterar(cliente);
    }
}
