package modelo;
/**
 * A classe Area representa uma area do sistema. Possui informações
 * como nome e palavra chave da área.
 */
public class Area {
    private String nome, palavra_chave;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPalavra_chave() {
        return palavra_chave;
    }

    public void setPalavra_chave(String palavra_chave) {
        this.palavra_chave = palavra_chave;
    }
    
}
