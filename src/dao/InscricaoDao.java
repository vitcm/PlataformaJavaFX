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

    public ArrayList<Integer> obterCodigosCursosDoAluno(String emailAluna) throws Exception {
        ArrayList<Integer> codigosCursos = new ArrayList<>();
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;

        try {
            sql = conn.prepareStatement("SELECT fk_codigocurso FROM inscricao WHERE fk_emailaluno = ?");
            sql.setString(1, emailAluna);
            rs = sql.executeQuery();

            while (rs.next()) {
                int codigoCurso = rs.getInt("fk_codigocurso");
                codigosCursos.add(codigoCurso);
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter códigos de cursos do aluno: " + e);
        } finally {
            Conexao.closeConnection(conn, sql, rs);
        }

        return codigosCursos;
    }

    public boolean excluiInscricao(int codigo) throws Exception {
        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        try {
            sql = conn.prepareStatement("DELETE FROM Inscricao where fk_codigocurso = (?)");
            sql.setInt(1, codigo);
            sql.executeUpdate();
        } catch (Exception e) {
            retorno = false;
        }
        return retorno;
    }
}
