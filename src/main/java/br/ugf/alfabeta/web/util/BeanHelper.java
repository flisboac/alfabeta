/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.util;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ana
 */
public class BeanHelper implements Serializable {

    private FacesContext facesContext;
    private HttpSession httpSession;

    public BeanHelper() {
    }

    public BeanHelper(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public BeanHelper(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public BeanHelper(FacesContext facesContext, HttpSession httpSession) {
        this.facesContext = facesContext;
        this.httpSession = httpSession;
    }

    public FacesContext getFacesContext() {
        FacesContext context = this.facesContext;

        if (context == null) {
            context = FacesContext.getCurrentInstance();
        }

        if (context == null) {
            throw new NullPointerException("Não foi possível obter um FacesContext.");
        }

        return context;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public void setSessao(HttpSession sessao) {
        this.httpSession = sessao;
    }

    public HttpSession getSessao() {

        return getSessao(false);
    }

    public HttpSession getSessao(boolean criar) {

        HttpSession sessao = this.httpSession;
        
        if (sessao == null) {
            FacesContext context = getFacesContext();

            sessao = (HttpSession) context
                    .getExternalContext()
                    .getSession(criar);
        }

        if (sessao == null && criar) {
            throw new NullPointerException("Não foi possível obter uma sessão.");
        }

        return sessao;
    }

    public HttpServletRequest getRequest() {

        HttpServletRequest request = (HttpServletRequest) getFacesContext()
                .getExternalContext()
                .getRequest();
        return request;
    }

    public HttpServletResponse getResponse() {

        HttpServletResponse request = (HttpServletResponse) getFacesContext()
                .getExternalContext()
                .getResponse();
        return request;
    }

    public Cliente getClienteLogado() {

        HttpSession sessao = getSessao(false);
        Cliente retorno = null;

        if (sessao != null) {
            retorno = (Cliente) sessao.getAttribute("alfabeta.cliente");
        }

        return retorno;
    }

    public void efetuarLoginCliente(Cliente cliente) {

        if (cliente != null) {
            HttpSession sessao = getSessao(false);

            if (sessao == null) {
                sessao = getSessao(true);
            }

            sessao.setAttribute("alfabeta.cliente", cliente);
        }
    }

    public void efetuarLogoffCliente() {

        HttpSession sessao = getSessao(false);

        if (sessao != null) {
            sessao.removeAttribute("alfabeta.cliente");
            sessao.invalidate();
        }
    }

    public boolean isClienteLogado() {

        return getClienteLogado() != null;
    }

    public Funcionario getFuncionarioLogado() {

        HttpSession sessao = getSessao(false);
        Funcionario retorno = null;

        if (sessao != null) {
            retorno = (Funcionario) sessao.getAttribute("alfabeta.funcionario");
        }

        return retorno;
    }

    public void efetuarLoginFuncionario(Funcionario funcionario) {

        if (funcionario != null) {
            HttpSession sessao = getSessao(false);

            if (sessao == null) {
                sessao = getSessao(true);
            }

            sessao.setAttribute("alfabeta.funcionario", funcionario);
        }
    }

    public void efetuarLogoffFuncionario() {

        HttpSession sessao = getSessao(false);

        if (sessao != null) {
            sessao.removeAttribute("alfabeta.funcionario");
            sessao.invalidate();
        }
    }

    public boolean isFuncionarioLogado() {

        return getFuncionarioLogado() != null;
    }

    public void mensagem(Severity severidade, String idComponente, String titulo, String mensagem) {

        FacesMessage message = new FacesMessage(severidade, titulo, mensagem);
        getFacesContext().addMessage(idComponente, message);
    }

    public void ok(String idComponente, String titulo, String mensagem) {

        mensagem(FacesMessage.SEVERITY_INFO, idComponente, titulo, mensagem);
    }

    public void ok(String titulo, String mensagem) {

        mensagem(FacesMessage.SEVERITY_INFO, null, titulo, mensagem);
    }

    public void ok(String mensagem) {

        mensagem(FacesMessage.SEVERITY_INFO, null, "OK!", mensagem);
    }

    public void warn(String idComponente, String titulo, String mensagem) {

        mensagem(FacesMessage.SEVERITY_WARN, idComponente, titulo, mensagem);
    }

    public void warn(String titulo, String mensagem) {

        mensagem(FacesMessage.SEVERITY_WARN, null, titulo, mensagem);
    }

    public void warn(String mensagem) {

        mensagem(FacesMessage.SEVERITY_WARN, null, "OK!", mensagem);
    }

    public void erro(String idComponente, String titulo, String mensagem) {

        mensagem(FacesMessage.SEVERITY_ERROR, idComponente, titulo, mensagem);
    }

    public void erro(String titulo, String mensagem) {

        mensagem(FacesMessage.SEVERITY_ERROR, null, titulo, mensagem);
    }

    public void erro(String mensagem) {

        mensagem(FacesMessage.SEVERITY_ERROR, null, "OK!", mensagem);
    }
}
