/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.web.beans;

import br.ugf.alfabeta.modelo.clientes.Cliente;
import br.ugf.alfabeta.modelo.clientes.ClienteDlo;
import br.ugf.alfabeta.modelo.clientes.ClienteDloImpl;
import br.ugf.alfabeta.modelo.editoras.Editora;
import br.ugf.alfabeta.modelo.editoras.EditoraDlo;
import br.ugf.alfabeta.modelo.editoras.EditoraDloImpl;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.funcionarios.Funcionario;
import br.ugf.alfabeta.modelo.funcionarios.FuncionarioDlo;
import br.ugf.alfabeta.modelo.funcionarios.FuncionarioDloImpl;
import br.ugf.alfabeta.modelo.livros.Livro;
import br.ugf.alfabeta.modelo.livros.LivroDlo;
import br.ugf.alfabeta.modelo.livros.LivroDloImpl;
import br.ugf.alfabeta.web.util.Bean;
import br.ugf.alfabeta.web.util.Prefixos;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author flisboac
 */
@ManagedBean
@ViewScoped
public class InstalacaoBean extends Bean {
    
    private static final String[] Numeros = {
        "Void",
        "Uno",
        "Dos",
        "Tres",
        "Quatro",
        "Cinco",
        "Ses",
        "Sete",
        "Oito",
        "Nove"
    };
    
    private static final int NumeroEditoras = 3;
    private static final int NumeroLivrosPorEditora = 5;
    private static final int QuantidadeLivrosBase = 20;
    private static final int QuantidadeLivrosMinima = 10;
    private static final double PrecoBaseEditora = 10.2;
    private static final double PrecoBaseLivro = 1.1;
    
    private transient FuncionarioDlo funcionarioDlo = new FuncionarioDloImpl();
    private transient ClienteDlo clienteDlo = new ClienteDloImpl();
    private transient EditoraDlo editoraDlo = new EditoraDloImpl();
    private transient LivroDlo livroDlo = new LivroDloImpl();
    
    public void instalar() {
        
        // Inserindo super-usuário
        
        Funcionario funcionarioRoot = new Funcionario();
        funcionarioRoot.setEmail("root@localhost");
        funcionarioRoot.setMatricula("root");
        funcionarioRoot.setNome("Super-usuário");
        funcionarioRoot.setSenha("root");
        
        boolean rootExiste;
        try {
            rootExiste = funcionarioDlo.existe(funcionarioRoot);
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao verificar a existência do super-usuário.");
            return;
        }
        
        if (!rootExiste) {
            try {
                funcionarioDlo.inserir(funcionarioRoot);
                
            } catch (ExcecaoDlo ex) {
                getHelper().erro("Erro ao inserir super-usuário.");
                return;
            }
        }
        
        // Inserindo usuário comum
        
        Cliente clienteComum = new Cliente();
        clienteComum.setEmail("cliente@localhost");
        clienteComum.setSenha("cliente");
        clienteComum.setNome("Cliente Comum");
        
        boolean clienteExiste;
        try {
            clienteExiste = clienteDlo.existe(clienteComum);
            
        } catch (ExcecaoDlo ex) {
            getHelper().erro("Erro ao verificar a existência do cliente comum.");
            return;
        }
        
        if (!clienteExiste) {
            try {
                clienteDlo.inserir(clienteComum);
                
            } catch (ExcecaoDlo ex) {
                getHelper().erro("Erro ao inserir cliente comum.");
                return;
            }
        }
        
        // Inserindo Editoras e livros
        
        for (int idxEditora = 0; idxEditora < NumeroEditoras; idxEditora++) {
            
            Editora editora = new Editora();
            editora.setCodigo(Prefixos.Editora + idxEditora);
            editora.setNome("Editora " + Numeros[idxEditora]);
            editora.setEndereco("Av. " + Numeros[idxEditora] + ", " + (idxEditora + 1) + "00");
            
            boolean editoraExiste;
            try {
                editoraExiste = editoraDlo.existe(editora);
                
            } catch (ExcecaoDlo ex) {
                getHelper().erro("Erro ao verificar existência da editora '" + editora.getNome() + "'.");
                return;
            }
            
            if (!editoraExiste) {
                try {
                    editoraDlo.inserir(editora);

                } catch (ExcecaoDlo ex) {
                    getHelper().erro("Erro ao inserir editora '" + editora.getNome() + "'.");
                    return;
                }
            }
            
            try {
                editora = editoraDlo.obterPorCodigo(editora.getCodigo());
                
            } catch (ExcecaoDlo ex) {
                getHelper().erro("Erro ao obter editora '" + editora.getNome() + "'.");
                return;
            }
            
            for (int idxLivro = 0; idxLivro < NumeroLivrosPorEditora; idxLivro++) {
                
                Livro livro = new Livro();
                livro.setEditora(editora);
                livro.setCodigo(Prefixos.Livro + idxEditora + "-" + idxLivro);
                livro.setNome(Numeros[idxEditora] + " " + Numeros[idxLivro]);
                livro.setPreco(PrecoBaseEditora * (idxEditora + 1) + PrecoBaseLivro * (idxLivro + 1));
                livro.setQuantidade(QuantidadeLivrosBase);
                livro.setQuantidadeMinima(QuantidadeLivrosMinima);
                
                boolean livroExiste;
                try {
                    livroExiste = livroDlo.existe(livro);
                    
                } catch (ExcecaoDlo ex) {
                    getHelper().erro("Erro ao verificar existência do livro '" + livro.getNome() + "'.");
                    return;
                }
                
                if (!livroExiste) {
                    try {
                        livroDlo.inserir(livro);

                    } catch (ExcecaoDlo ex) {
                        getHelper().erro("Erro ao inserir livro '" + livro.getNome() + "'.");
                        return;
                    }
                }
            }
        }
        
        // FINISHED
        getHelper().ok("Carga inicial inserida com sucesso.");
    }
    
    public void invalidarSessao() {
        
        HttpSession sessao = getHelper().getSessao(false);
        
        if (sessao != null) {
            sessao.invalidate();
            getHelper().ok("Sessão invalidada com sucesso.");
            
        } else {
            getHelper().ok("Não há sessão para invalidar.");
        }
    }
}
