package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Curso;

public class CursoDao {

    public boolean adicionaCurso(Curso curso, int codArea, String emailAdmin) throws Exception {
        boolean retorno = true;
        Connection con = Conexao.getConnection();
        PreparedStatement sql = null;

        try {
            sql = con.prepareStatement("INSERT INTO Curso (nomecurso, pchavecurso, choraria, valor, fk_codigoarea, fk_emailadmin) VALUES ((?),(?),(?),(?),(?),(?))");
            sql.setString(1, curso.getTitulo());
            sql.setString(2, curso.getPalavra_chave_curso());
            sql.setInt(3, curso.getC_horaria());
            sql.setDouble(4, curso.getValor());
            sql.setInt(5, codArea);
            sql.setString(6, emailAdmin);
            sql.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erro =" + ex);
            retorno = false;
        } finally {
            Conexao.closeConnection(con, sql);
        }
        return retorno;
    }

    public int recuperarCodigoCurso(String tituloCurso) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection con = Conexao.getConnection();
        int codigoCurso = -1; // Valor padrão caso o código não seja encontrado

        try {
            String sql = "SELECT codigocurso FROM curso WHERE nomecurso = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, tituloCurso);
            resultSet = statement.executeQuery();
            // Verificar se algum resultado foi retornado
            if (resultSet.next()) {
                codigoCurso = resultSet.getInt("codigocurso");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar o código da área do banco de dados.");
            e.printStackTrace();
        } finally {
            // Fechar as conexões e recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar as conexões com o banco de dados.");
                e.printStackTrace();
            }
        }
        return codigoCurso;
    }

    public Curso pesquisaCurso(String tituloCurso) throws Exception {
        String mensagem = "";
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        Curso curso = new Curso();
        try {
            sql = conn.prepareStatement("SELECT Curso (nomecurso, pchavecurso, choraria, valor, fk_codigoarea, fk_emailCurso) where codigocurso=(?)");
            sql.setString(1, tituloCurso);
            rs = sql.executeQuery();
            if (rs != null) {
                mensagem = "";
                while (rs.next()) {
                    curso.setTitulo(rs.getString("nomecurso"));
                    curso.setPalavra_chave_curso(rs.getString("pchavecurso"));
                    curso.setValor(rs.getDouble("valor"));
                    curso.setC_horaria(rs.getInt("choraria"));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        } finally {
            Conexao.closeConnection(conn, sql, rs);
        }
        return curso;
    }

    public boolean verificaCursoExistente(String titulo) throws SQLException, Exception {
        Connection conn = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean cursoExistente = false;

        try {
            String sql = "SELECT COUNT(*) FROM curso WHERE nomecurso = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, titulo);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                cursoExistente = (count > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, statement, resultSet);
        }
        return cursoExistente;
    }

    public boolean alteraCurso(Curso curso, int codigo) throws Exception {
        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        try {
            sql = conn.prepareStatement("UPDATE Curso SET nomecurso = (?), pchavecurso = (?), valor = (?), choraria = (?) WHERE codigocurso = (?);");
            sql.setString(1, curso.getTitulo());
            sql.setString(2, curso.getPalavra_chave_curso());
            sql.setDouble(3, curso.getValor());
            sql.setInt(4, curso.getC_horaria());
            sql.setInt(5, codigo);
            sql.executeUpdate();
        } catch (Exception e) {
            retorno = false;
        }
        return retorno;
    }

    public boolean excluiCurso(int codigo) throws Exception {
        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        try {
            sql = conn.prepareStatement("DELETE FROM Curso where codigocurso = (?)");
            sql.setInt(1, codigo);
            sql.executeUpdate();
        } catch (Exception e) {
            retorno = false;
        }
        return retorno;
    }

    public ArrayList<Curso> obterCursosDoAdministrador(String emailAdmin) throws Exception {
        ArrayList<Curso> cursos = new ArrayList<>();
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;

        try {
            sql = conn.prepareStatement("SELECT * FROM Curso WHERE fk_emailadmin = ?");
            sql.setString(1, emailAdmin);
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
            System.out.println("Erro ao obter cursos do administrador: " + e);
        } finally {
            Conexao.closeConnection(conn, sql, rs);
        }

        return cursos;
    }
    
    public ArrayList<Curso> obterCursos() throws Exception {
        ArrayList<Curso> cursos = new ArrayList<>();
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;

        try {
            sql = conn.prepareStatement("SELECT * FROM Curso");
            rs = sql.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("codigocurso");
                String tituloCurso = rs.getString("nomecurso");
                String palavraChave = rs.getString("pchavecurso");
                int cHoraria = rs.getInt("choraria");
                double valor = rs.getDouble("valor");

                Curso curso = new Curso();
                curso.setCodigo(codigo);
                curso.setTitulo(tituloCurso);
                curso.setPalavra_chave_curso(palavraChave);
                curso.setC_horaria(cHoraria);
                curso.setValor(valor);

                cursos.add(curso);
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter cursos do administrador: " + e);
        } finally {
            Conexao.closeConnection(conn, sql, rs);
        }

        return cursos;
    }

}
