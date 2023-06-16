package negocio;

import dao.AlunaDao;
import modelo.Aluna;

public class AlunaNegocio {

    //Valida email e a senha para login
    public boolean validaDadosLogin(String email, String senha) throws Exception {
        boolean retorno = false;
        AlunaDao alnDao = new AlunaDao();
        Aluna aln = new Aluna();
        aln = alnDao.pesquisaContato(email);
        try {
            if (aln.getEmailAluna().equals(email) && aln.getSenhaAluna().equals(senha)) {
                retorno = true;
            }
        } catch (Exception e) {
            retorno = false;
        }
        return retorno;
    }

    //Valida a senha para a tela de CONFIRMAÇÃO de alteração
    public boolean validaSenhaAdmin(String email, String senha) throws Exception {
        boolean retorno;
        AlunaDao alnDao = new AlunaDao();
        Aluna aln = new Aluna();
        aln = alnDao.pesquisaContato(email);
        if (aln.getSenhaAluna().equals(senha)) {
            retorno = true;
        } else {
            retorno = false;
        }
        return retorno;
    }

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
        if (senha.length() == 0) {
            cont++;
        }
        if (cont != 0) {
            retorno = false;
        }
        return retorno;
    }

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

    public boolean verificaSobrenomeVazio(String sobrenome) {
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

    public boolean verificaValidadeEmail(String email) {
        boolean sim = false;
        int cont = 0;
        if (!email.endsWith(".com")) {
            cont++;
        }
        if (!email.contains("@")) {
            cont++;
        }
        if (cont == 0) {
            sim = true;
        }
        return sim;
    }

    public boolean verificaValidadeSenha(String senha) {
        boolean sim = false;
        int cont = 0;
        if (!senha.matches("\\d{6}")) {
            cont++;
        }
        if (cont == 0) {
            sim = true;
        }
        return sim;
    }

    public boolean verificaValidadeNome(String nome) {
        boolean sim = false;
        int cont = 0;
        if (!nome.matches("[a-zA-ZÀ-ÿ]+")) {
            cont++;
        }
        if (cont == 0) {
            sim = true;
        }
        return sim;
    }
}
