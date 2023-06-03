package conexao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Conexao 
{
    //private static final String login="vitorya";
    //private String senha="vitorya";
    private String host="localhost:5432";
    private String dbName="BDeMVC";
    private static final String USER = "postgres";
    private static final String PASS = "200609";
    private static final String URL = "jdbc:postgresql://localhost:5432/BDeMVC";
    
    public Conexao()
    {
        
    }
    
    public static Connection getConnection() throws Exception{
        Connection con = null;
        try 
        {
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection(URL, USER, PASS);
            return con;
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
            throw new RuntimeException("Erro na conexão: ",ex);
        }
            
    }
    
    public static void closeConnection(Connection con){
            try {
                if(con!=null){
                con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }       
    }
    
    public static void closeConnection(Connection con,PreparedStatement sql){
            closeConnection(con);
            try {
                if(sql!=null){
                sql.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }       
    }
    
    public static void closeConnection(Connection con,PreparedStatement sql,ResultSet rs){
            closeConnection(con,sql);
            try {
                if(rs!=null){
                rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }       
    }
}
