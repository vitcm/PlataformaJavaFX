package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Aluna;
/**
 * Conexão com o banco de dados - conexão com a tabela ALUNO
 */
public class AlunaDao {
    public boolean adicionaAluna(Aluna aln) throws Exception{
        boolean retorno = true;
        Connection con = Conexao.getConnection();
        PreparedStatement sql = null;
        
        try{
            sql = con.prepareStatement("INSERT INTO Aluno (nomeAluno, sobrenomeAluno, emailAluno, senhaAluno) VALUES ((?),(?),(?),(?))");
            sql.setString(1,aln.getNomeAluna());
            sql.setString(2,aln.getSobrenomeAluna());
            sql.setString(3,aln.getEmailAluna());
            sql.setString(4,aln.getSenhaAluna());
            sql.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println("erro =" +ex);
            retorno=false;
        }
        finally
        {
            Conexao.closeConnection(con, sql);
        }
        return retorno;
    }
    
    public Aluna pesquisaContato(String emailAluna) throws Exception
    {
        String mensagem="";
        Connection conn = Conexao.getConnection();
        PreparedStatement sql= null;
        ResultSet rs= null;
        Aluna aln = new Aluna();
        try
        {
            sql = conn.prepareStatement("SELECT nomeAluno, sobrenomeAluno, emailAluno,"
                    + " senhaAluno from Aluno where emailAluno=(?)");
            sql.setString(1, emailAluna);
            rs=sql.executeQuery();
            if(rs!=null){
                mensagem = "";
                while(rs.next()){
                    aln.setNomeAluna(rs.getString("nomeAluno"));
                    aln.setSobrenomeAluna(rs.getString("sobrenomeAluno"));
                    aln.setEmailAluna(rs.getString("emailAluno"));
                    aln.setSenhaAluna(rs.getString("senhaAluno"));
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Erro: "+e);
        }
        finally{
            Conexao.closeConnection(conn,sql,rs);
        }
        return aln;
    }
    
    public boolean alteraAluna(Aluna aln, String email) throws Exception{
        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement sql= null;
        try
        {
            sql= conn.prepareStatement("UPDATE Aluno SET nomeAluno = (?), sobrenomeAluno = (?), senhaAluno = (?) WHERE emailAluno = (?);");
            sql.setString(1,aln.getNomeAluna());
            sql.setString(2,aln.getSobrenomeAluna());
            sql.setString(3,aln.getSenhaAluna());
            sql.setString(4,email);
            sql.executeUpdate();
        }
        catch(Exception e)
        {
            retorno=false;
        }
        return retorno;
    }
    
    public boolean excluiAluna(String email) throws Exception{
        boolean retorno=true;
        Connection conn = Conexao.getConnection();
        PreparedStatement sql= null;
        try
        {
            sql= conn.prepareStatement("DELETE FROM Aluno where emailAluno = (?)");
            sql.setString(1,email);
            sql.executeUpdate();
        }catch(Exception e){
            retorno=false;
        }
        return retorno;
    }
}
