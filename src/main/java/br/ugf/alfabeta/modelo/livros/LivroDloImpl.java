/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.livros;

import br.ugf.alfabeta.modelo.editoras.Editora;
import br.ugf.alfabeta.modelo.editoras.ValidadorEditora;
import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.entidades.Validador;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;
import br.ugf.alfabeta.modelo.validacoes.Consulta;
import java.util.List;

/**
 *
 * @author ubuntu
 */
public class LivroDloImpl extends EntidadeDloPersistencia<Livro> implements LivroDlo {
    
    public LivroDloImpl() {
        super(new LivroDaoImpl(), new ValidadorLivro());
    }
    
    public LivroDloImpl(LivroDao dao) {
        super(dao, new ValidadorLivro());
    }

    @Override
    public List<Livro> listarPorEditora(Editora editora) throws ExcecaoDlo {
        
        List<Livro> retorno = null;
        LivroDao entidadeDao = (LivroDao) getDao();
        Validador<Editora> validadorEditora = new ValidadorEditora();
        
        validadorEditora.validar(editora, Consulta.class);
        
        try {
            retorno = entidadeDao.listarPorEditora(editora);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(ex.getMessage(), ex);
        }
        
        if (retorno == null) {
            throw new ExcecaoPersistenciaDlo("Lista de livros nula retornada.");
        }
        
        return retorno;
    }
}
