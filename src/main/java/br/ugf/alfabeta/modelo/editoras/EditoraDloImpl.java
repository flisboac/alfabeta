/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.editoras;

import br.ugf.alfabeta.modelo.entidades.EntidadeDloPersistencia;

/**
 *
 * @author Ana
 */
public class EditoraDloImpl extends EntidadeDloPersistencia<Editora> implements EditoraDlo {
    
    public EditoraDloImpl() {
        super(new EditoraDaoImpl());
    }
    
    public EditoraDloImpl(EditoraDao dao) {
        super(dao);
    }
}
