package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * A classe Conexao é responsável por estabelecer a conexão com o banco de dados PostgreSQL.
 */
public class Conexao {
    private String host = "localhost:5432";
    private String dbName = "VitoryaCursos-062023";
    private static final String USER = "postgres";
    private static final String PASS = "200609";
    private static final String URL = "jdbc:postgresql://localhost:5432/VitoryaCursos-062023";

    /**
     * Cria uma nova instância da classe Conexao.
     */
    public Conexao() {

    }

    /**
     * Obtém uma conexão com o banco de dados.
     *
     * @return a conexão estabelecida com o banco de dados
     * @throws Exception se ocorrer um erro durante a conexão
     */
    public static Connection getConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }
    }

    /**
     * Fecha a conexão com o banco de dados.
     *
     * @param con a conexão a ser fechada
     */
    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Fecha a conexão com o banco de dados e o PreparedStatement.
     *
     * @param con a conexão a ser fechada
     * @param sql o PreparedStatement a ser fechado
     */
    public static void closeConnection(Connection con, PreparedStatement sql) {
        closeConnection(con);
        try {
            if (sql != null) {
                sql.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Fecha a conexão com o banco de dados, o PreparedStatement e o ResultSet.
     *
     * @param con a conexão a ser fechada
     * @param sql o PreparedStatement a ser fechado
     * @param rs o ResultSet a ser fechado
     */
    public static void closeConnection(Connection con, PreparedStatement sql, ResultSet rs) {
        closeConnection(con, sql);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
