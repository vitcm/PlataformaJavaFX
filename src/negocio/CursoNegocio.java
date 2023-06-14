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

    public boolean verificaChoraria(String choraria) {
        boolean retorno = true;
        int cont = 0;
        if (choraria.length() == 0) {
            cont++;
        } else {
            try {
                int chorariaInt = Integer.parseInt(choraria);
            } catch (NumberFormatException e) {
                cont++;
            }
        }
        if (cont != 0) {
            retorno = false;
        }
        return retorno;
    }

    public boolean verificaValor(String valor) {
        boolean retorno = true;
        int cont = 0;
        if (valor.length() == 0) {
            cont++;
        } else {
            try {
                double valorDouble = Double.parseDouble(valor);
            } catch (NumberFormatException e) {
                cont++;
            }
        }
        if (cont != 0) {
            retorno = false;
        }
        return retorno;
    }

}
