package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Curso;

public class InscricaoDao {
    public boolean adicionaCurso(int codigoCurso, String emailAluna) throws Exception {
        boolean retorno = true;
        Connection con = Conexao.getConnection();
        PreparedStatement sql = null;

        try {
            sql = con.prepareStatement("INSERT INTO Inscricao (fk_codigocurso, fk_emailaluno) VALUES ((?),(?))");
            sql.setInt(1, codigoCurso);
            sql.setString(2, emailAluna);
            sql.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erro =" + ex);
            retorno = false;
        } finally {
            Conexao.closeConnection(con, sql);
        }
        return retorno;
    }
    
    public ArrayList<Curso> obterCursosDoAluno(String emailAluna) throws Exception {
        ArrayList<Curso> cursos = new ArrayList<>();
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;

        try {
            sql = conn.prepareStatement("SELECT * FROM inscricao WHERE fk_emailaluno = ?");
            sql.setString(1, emailAluna);
            rs = sql.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("codigocurso");
                String tituloCurso = rs.getString("nomecurso");
                String palavraChave = rs.getString("pchavecurso");
                int cHoraria = rs.getInt("choraria");
                double valor = rs.getDouble("valor");
                int codigoArea = rs.getInt("fk_codigoarea");

                Curso curso = new Curso();
                curso.setCodigo(codigo);
                curso.setTitulo(tituloCurso);
                curso.setPalavra_chave_curso(palavraChave);
                curso.setC_horaria(cHoraria);
                curso.setValor(valor);
                curso.setArea(codigoArea);
                

                cursos.add(curso);
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter cursos do aluno: " + e);
        } finally {
            Conexao.closeConnection(conn, sql, rs);
        }

        return cursos;
    }
}
