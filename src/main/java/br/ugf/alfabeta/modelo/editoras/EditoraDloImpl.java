/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.editoras;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoCriticaDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDao;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoDlo;
import br.ugf.alfabeta.modelo.excecoes.ExcecaoPersistenciaDlo;

/**
 *
 * @author Ana
 */
public class EditoraDloImpl extends EntidadeDloPersistencia<Editora> implements EditoraDlo {
    
    public EditoraDloImpl() {
        super(new EditoraDaoImpl(), new ValidadorEditora());
    }
    
    public EditoraDloImpl(EditoraDao dao) {
        super(dao, new ValidadorEditora());
    }

    @Override
    public Editora obterPorCodigo(String codigo) throws ExcecaoDlo {
        
        
        if (codigo == null || codigo.isEmpty()) {
            throw new ExcecaoCriticaDlo("E-mail não pode ser nulo ou vazio.");
        }
        
        EditoraDao editoraDao = (EditoraDao)getDao();
        Editora retorno = null;
        try {
            retorno = editoraDao.obterPorCodigo(codigo);
            
        } catch (ExcecaoDao ex) {
            throw new ExcecaoPersistenciaDlo(
                    "Erro ao obter editora com código '" + codigo + "'.", ex);
        }
        
        return retorno;
    }
}
