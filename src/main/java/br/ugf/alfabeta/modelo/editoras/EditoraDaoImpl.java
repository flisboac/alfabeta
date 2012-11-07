/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ugf.alfabeta.modelo.editoras;

import br.ugf.alfabeta.modelo.entidades.JpaDao;

/**
 *
 * @author Ana
 */
public class EditoraDaoImpl extends JpaDao<Editora> implements EditoraDao {
    
    public EditoraDaoImpl() {
        super(Editora.class);
    }
}
