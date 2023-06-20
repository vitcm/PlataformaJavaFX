package negocio;
/**
 * A classe AreaNegocio representa as regras de negócio do projeto.
 * Aqui estão especificadas as regras que envolvem o objeto Area.
 */
public class AreaNegocio {
        public boolean verificaNomeVazio(String nome) {
        boolean retorno = true;
        int cont = 0;
        if (nome.length() == 0) {
            cont++;
        }
        if (cont != 0) {
            retorno = false;
        }
        return retorno;
    }
    
    public boolean verificaPalavraChaveVazia(String sobrenome) {
        boolean retorno = true;
        int cont = 0;
        if (sobrenome.length() == 0) {
            cont++;
        }
        if (cont != 0) {
            retorno = false;
        }
        return retorno;
    }
}
