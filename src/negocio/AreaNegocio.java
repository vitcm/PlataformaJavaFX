package negocio;

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
