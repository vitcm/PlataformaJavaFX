package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Area;

public class AreaDao {

    public boolean adicionaArea(Area area) throws Exception {
        boolean retorno = true;
        Connection con = Conexao.getConnection();
        PreparedStatement sql = null;

        try {
            sql = con.prepareStatement("INSERT INTO Area (nomeArea, pchavearea) VALUES ((?),(?))");
            sql.setString(1, area.getNome());
            sql.setString(2, area.getPalavra_chave());
            sql.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erro =" + ex);
            retorno = false;
        } finally {
            Conexao.closeConnection(con, sql);
        }
        return retorno;
    }

    public int recuperarCodigoArea(String nomeArea) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection con = Conexao.getConnection();
        int codigoArea = -1; // Valor padrão caso o código não seja encontrado

        try {
            String sql = "SELECT codigoarea FROM area WHERE nomearea = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, nomeArea);
            resultSet = statement.executeQuery();
            // Verificar se algum resultado foi retornado
            if (resultSet.next()) {
                codigoArea = resultSet.getInt("codigoarea");
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
        return codigoArea;
    }

    public Area pesquisaArea(String nome) throws Exception {
        String mensagem = "";
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        Area area = new Area();
        try {
            sql = conn.prepareStatement("SELECT nomeArea, palavra_chave from Area where nomearea=(?)");
            sql.setString(1, nome);
            rs = sql.executeQuery();
            if (rs != null) {
                mensagem = "";
                while (rs.next()) {
                    area.setNome(rs.getString("nomeArea"));
                    area.setPalavra_chave(rs.getString("pchavearea"));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        } finally {
            Conexao.closeConnection(conn, sql, rs);
        }
        return area;
    }

    public ArrayList<String> obterNomesAreas() throws Exception {
        ArrayList<String> nomesAreas = new ArrayList<>();
        Connection conn = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT nomeArea FROM Area";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nomeArea = rs.getString("nomeArea");
                nomesAreas.add(nomeArea);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter nomes de áreas do banco de dados.");
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, stmt, rs);
        }

        return nomesAreas;
    }

    public boolean verificaAreaExistente(String nome) throws SQLException, Exception {
        Connection conn = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean areaExistente = false;

        try {
            String sql = "SELECT COUNT(*) FROM area WHERE UPPER(nomearea) = UPPER(?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, nome.toUpperCase());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                areaExistente = (count > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, statement, resultSet);
        }
        return areaExistente;
    }

    public boolean alteraArea(Area area) throws Exception {
        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        try {
            sql = conn.prepareStatement("UPDATE Area SET nomeArea = (?), pchavearea = (?) WHERE nomeArea = (?);");
            sql.setString(1, area.getNome());
            sql.setString(2, area.getPalavra_chave());
            sql.executeUpdate();
        } catch (Exception e) {
            retorno = false;
        }
        return retorno;
    }

    public boolean excluiArea(String nome) throws Exception {
        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        try {
            sql = conn.prepareStatement("DELETE FROM Area where nomeArea = (?)");
            sql.setString(1, nome);
            sql.executeUpdate();
        } catch (Exception e) {
            retorno = false;
        }
        return retorno;
    }
}
