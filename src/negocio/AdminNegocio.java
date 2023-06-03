package negocio;

import dao.AdminDao;
import modelo.Admin;

public class AdminNegocio {

    AdminDao admDao = new AdminDao();

    //Valida email e a senha para login
    public boolean validaDadosLogin(String email, String senha) throws Exception {
        boolean retorno;

        Admin adm = new Admin();
        adm = admDao.pesquisaContato(email);
        if (adm.getEmailAdmin().equals(email) && adm.getSenhaAdmin().equals(senha)) {
            retorno = true;
        } else {
            retorno = false;
        }
        return retorno;
    }

    //Valida a senha para a tela de CONFIRMAÇÃO de alteração
    public boolean validaSenhaAdmin(String email, String senha) throws Exception {
        boolean retorno;
        AdminDao admDao = new AdminDao();
        Admin adm = new Admin();
        adm = admDao.pesquisaContato(email);
        if (adm.getSenhaAdmin().equals(senha)) {
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
        if (senha.length() != 6) {
            cont++;
        }
        if (cont != 0) {
            retorno = false;
        }
        return retorno;
    }
}
