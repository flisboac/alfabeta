/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.clientes.ClienteDlo;
import br.ugf.alfabeta.modelo.clientes.ClienteDloImpl;
import br.ugf.alfabeta.modelo.entidades.Dlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.web.entidades.CadastroBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author flisboac
 */
@ManagedBean
@ViewScoped
public class LoginClienteBean extends CadastroBean<Cliente> {
    
    private transient ClienteDlo clienteDlo = new ClienteDloImpl();    
    private Cliente cliente = new Cliente();
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public String efetuarLogin() {
        
        Cliente clienteReal = null;
        String mensagemOk = null;
        String mensagemErro = null;
        String outcome = "";
        
        try {
            clienteReal = this.clienteDlo.obterPorEmail(this.cliente.getEmail());    
        } catch (ExcecaoDlo ex) {}
        
        if (clienteReal == null) {
            mensagemErro = "E-mail ou senha inválidos.";
            
        } else {
            String senhaReal = clienteReal.getSenha();
            String senha = cliente.getSenha();
            
            if ((senhaReal == null && senha == null) ||
                    senhaReal != null && senhaReal.equals(senha)) {
                
                getHelper().efetuarLoginCliente(clienteReal);
                
            } else {
                mensagemErro = "E-mail ou senha inválidos.";
            }
        }
        
        if (mensagemErro != null) {
            getHelper().erro(mensagemErro);
            
        } else {
            mensagemOk = "Bem-vindo, " + clienteReal.getNome();
            
            outcome = (String) getHelper().getSessao().getAttribute("alfabeta.portal.proximaPagina");
            getHelper().getSessao().removeAttribute("alfabeta.portal.proximaPagina");
            
            if (outcome == null) {
                outcome = "/portal/index.xhtml";
            }
        }
        
        if (!outcome.isEmpty()) {
//            try {
//                getHelper().getResponse().sendRedirect(outcome);
//
//            } catch (IOException ex) {
//                getHelper().erro("Erro ao redirecionar para '" + outcome + "'.");
//            }
            
            outcome += "?faces-redirect=true";
        }
        
        if (mensagemOk != null) {
            getHelper().getSessao().setAttribute("alfabeta.mensagemOk", mensagemOk);
        }
        
        if (mensagemErro != null) {
            getHelper().getSessao().setAttribute("alfabeta.mensagemErro", mensagemErro);
        }
        
        return outcome;
    }
    
    public String efetuarLogoff() {
        
        String outcome = "";
        
        if (getHelper().isClienteLogado()) {
            getHelper().efetuarLogoffCliente();
            outcome = "/portal/index.xhtml?faces-redirect=true";
            getHelper().ok("Logoff efetuado com sucesso.");
        }
        
        return outcome;
    }

    @Override
    public Dlo<Cliente> getDlo() {
        return clienteDlo;
    }

    @Override
    public Cliente getEntidade() {
        return getCliente();
    }

    @Override
    public void setEntidade(Cliente cliente) {
        setCliente(cliente);
    }
}
