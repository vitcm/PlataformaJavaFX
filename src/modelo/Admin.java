package modelo;

public class Admin {
    private String nomeAdmin, sobrenomeAdmin, emailAdmin, senhaAdmin, senhaConfirmacaoAdmin;

    public String getSenhaConfirmacaoAdmin() {
        return senhaConfirmacaoAdmin;
    }

    public void setSenhaConfirmacaoAdmin(String emailConfirmacaoAdmin) {
        this.senhaConfirmacaoAdmin = emailConfirmacaoAdmin;
    }

    public String getNomeAdmin() {
        return nomeAdmin;
    }

    public void setNomeAdmin(String nomeAdmin) {
        this.nomeAdmin = nomeAdmin;
    }

    public String getSobrenomeAdmin() {
        return sobrenomeAdmin;
    }

    public void setSobrenomeAdmin(String sobrenomeAdmin) {
        this.sobrenomeAdmin = sobrenomeAdmin;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }

    public String getSenhaAdmin() {
        return senhaAdmin;
    }

    public void setSenhaAdmin(String senhaAdmin) {
        this.senhaAdmin = senhaAdmin;
    }
}
