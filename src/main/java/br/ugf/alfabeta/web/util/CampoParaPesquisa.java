
package br.ugf.alfabeta.web.util;

public enum CampoParaPesquisa {

    Editora("Editora", "editora"),
    Nome("Nome", "nome"),
    Codigo("Código", "codigo"),
    Preco("Preço", "preco");

    
    private String titulo;
    private String nomeCampo;

    private CampoParaPesquisa(String titulo, String nomeCampo) {
        this.titulo = titulo;
        this.nomeCampo = nomeCampo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }
    
    public static CampoParaPesquisa buscarPorCampo(String nomeCampo) {
        CampoParaPesquisa retorno = null;
        
        for (CampoParaPesquisa campoParaPesquisa : values()) {
            
            if (campoParaPesquisa.getNomeCampo().equals(nomeCampo)) {
                retorno = campoParaPesquisa;
                break;
            }
        }
        
        return retorno;
    }
}
