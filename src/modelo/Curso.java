package modelo;

public class Curso {
    private String titulo,palavra_chave_curso;
    private int c_horaria, codigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    private double valor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPalavra_chave_curso() {
        return palavra_chave_curso;
    }

    public void setPalavra_chave_curso(String palavra_chave_curso) {
        this.palavra_chave_curso = palavra_chave_curso;
    }

    public int getC_horaria() {
        return c_horaria;
    }

    public void setC_horaria(int c_horaria) {
        this.c_horaria = c_horaria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
