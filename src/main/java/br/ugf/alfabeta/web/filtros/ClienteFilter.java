/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.filtros;

import br.ugf.alfabeta.web.util.BeanHelper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author flisboac
 */
@WebFilter("/portal/*")
public class ClienteFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        BeanHelper helper = new BeanHelper();
        HttpSession sessao = req.getSession();
        helper.setSessao(sessao);
        String caminho = req.getRequestURI();
        
        if (caminho.startsWith(req.getContextPath() + "/portal/cliente")
                && !helper.isClienteLogado()) {
            if (sessao == null) {
                sessao = req.getSession(true);
            }
            sessao.setAttribute("alfabeta.portal.proximaPagina", caminho);
            sessao.setAttribute("alfabeta.mensagemErro", "É necessário efetuar login para acessar a página.");
            res.sendRedirect(req.getContextPath() + "/portal/login.xhtml");

        } else if (caminho.endsWith("login.xhtml") && helper.isClienteLogado()) {
            sessao.setAttribute("alfabeta.mensagemOk", "Você já está logado!");
            res.sendRedirect(req.getContextPath() + "/portal/index.xhtml");
            
        } else {
            
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
        // ...
    }

    @Override
    public void destroy() {
        // ..
    }

}