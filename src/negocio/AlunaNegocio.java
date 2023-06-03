package negocio;

public class AlunaNegocio {

    public boolean verificaEmailVazio(String email) {
        boolean retorno = true;
        int cont = 0;
        if (email.length() == 0) {
            cont++;
        }
        if (cont != 0) {
            retorno = false;
        }
        return retorno;
    }

    public boolean verificaSenhaVazio(String senha) {
        boolean retorno = true;
        int cont = 0;
        if (senha.length() != 6) {
            cont++;
        }
        if (cont != 0) {
            retorno = false;
        }
        return retorno;
    }
}
