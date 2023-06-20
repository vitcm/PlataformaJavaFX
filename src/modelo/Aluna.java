package modelo;
/**
 * A classe Auna representa um aluno do sistema. Possui informações
 * como nome, sobrenome, email e senha do aluno.
 */
public class Aluna {
    private String nomeAluna, sobrenomeAluna, emailAluna, senhaAluna;

    public String getNomeAluna() {
        return nomeAluna;
    }

    public void setNomeAluna(String nomeAluna) {
        this.nomeAluna = nomeAluna;
    }

    public String getSobrenomeAluna() {
        return sobrenomeAluna;
    }

    public void setSobrenomeAluna(String sobrenomeAluna) {
        this.sobrenomeAluna = sobrenomeAluna;
    }

    public String getEmailAluna() {
        return emailAluna;
    }

    public void setEmailAluna(String emailAluna) {
        this.emailAluna = emailAluna;
    }

    public String getSenhaAluna() {
        return senhaAluna;
    }

    public void setSenhaAluna(String senhaAluna) {
        this.senhaAluna = senhaAluna;
    }
}
