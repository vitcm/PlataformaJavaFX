package negocio;

public class CursoNegocio {

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

    public boolean verificaPalavraChaveVazia(String pchave) {
        boolean retorno = true;
        int cont = 0;
        if (pchave.length() == 0) {
            cont++;
        }
        if (cont != 0) {
            retorno = false;
        }
        return retorno;
    }

    public boolean verificaCbxAreaVazio(String nome) {
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

    public boolean verificaChorariaVazia(int choraria) {
        boolean retorno = true;
        int cont = 0;
        if (choraria<= 0) {
            cont++;
        }
        if (cont != 0) {
            retorno = false;
        }
        return retorno;
    }
    
    private boolean validaChoraria(String choraria) {
    try {
        Integer.parseInt(choraria);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

    public boolean verificaValorVazio(double valor) {
        boolean retorno = true;
        int cont = 0;
        if (valor<= 0) {
            cont++;
        }
        if (cont != 0) {
            retorno = false;
        }
        return retorno;
    }

}
