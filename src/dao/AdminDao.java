package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Admin;

/**
 * Conexão com o banco de dados - conexão com a tabela ADMIN
 */
public class AdminDao {

    public boolean adicionaAdmin(Admin adm) throws Exception {
        boolean retorno = true;
        Connection con = Conexao.getConnection();
        PreparedStatement sql = null;

        try {
            sql = con.prepareStatement("INSERT INTO Admin (nomeAdmin, sobrenomeAdmin, emailAdmin, senhaAdmin) VALUES ((?),(?),(?),(?))");
            sql.setString(1, adm.getNomeAdmin());
            sql.setString(2, adm.getSobrenomeAdmin());
            sql.setString(3, adm.getEmailAdmin());
            sql.setString(4, adm.getSenhaAdmin());
            sql.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erro =" + ex);
            retorno = false;
        } finally {
            Conexao.closeConnection(con, sql);
        }
        return retorno;
    }

    public Admin pesquisaContato(String emailAdmin) throws Exception {
        String mensagem = "";
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        Admin adm = new Admin();
        try {
            sql = conn.prepareStatement("SELECT nomeAdmin, sobrenomeAdmin, emailAdmin,"
                    + " senhaAdmin from admin where emailAdmin=(?)");
            sql.setString(1, emailAdmin);
            rs = sql.executeQuery();
            if (rs != null) {
                mensagem = "";
                while (rs.next()) {
                    adm.setNomeAdmin(rs.getString("nomeAdmin"));
                    adm.setSobrenomeAdmin(rs.getString("sobrenomeAdmin"));
                    adm.setEmailAdmin(rs.getString("emailAdmin"));
                    adm.setSenhaAdmin(rs.getString("senhaAdmin"));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        } finally {
            Conexao.closeConnection(conn, sql, rs);
        }
        return adm;
    }

    public boolean alteraAdmin(Admin adm, String email) throws Exception {
        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        try {
            sql = conn.prepareStatement("UPDATE Admin SET nomeAdmin = (?), sobrenomeAdmin = (?), senhaAdmin = (?) WHERE emailAdmin = (?);");
            sql.setString(1, adm.getNomeAdmin());
            sql.setString(2, adm.getSobrenomeAdmin());
            sql.setString(3, adm.getSenhaAdmin());
            sql.setString(4, email);
            sql.executeUpdate();
        } catch (Exception e) {
            retorno = false;
            e.printStackTrace();
        }
        return retorno;
    }

    public boolean excluiAdmin(String email) throws Exception {
        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement sql = null;
        try {
            sql = conn.prepareStatement("DELETE FROM Admin where emailAdmin = (?)");
            sql.setString(1, email);
            sql.executeUpdate();
        } catch (Exception e) {
            retorno = false;
        }
        return retorno;
    }
}
