package fabrisoft.sql;

import fabrisoft.util.Mensagem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Banco {

    private static void criaDataBase() {
        try {
            Connection conn=getConexaoParaCriar();
         Statement stmt = null;
        stmt = conn.createStatement();

        String sql = "CREATE DATABASE STUDENTS";
        
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private final int VERSAO=1;
    
    private static Connection getConexaoParaCriar(){
          try {
           Class.forName("org.postgresql.Driver");
            return DriverManager //localhost
                    .getConnection("jdbc:postgresql://localhost:5432/",
                    "postgres",
                    "postgres123");/**/
        } catch (ClassNotFoundException ex) {
            String Erro=ex.getMessage();
        } catch (SQLException ex) {
            String Erro=ex.getMessage();
        }
        return null;
    }
    public static Connection getConexao(){
          try {
           Class.forName("org.postgresql.Driver");
            return DriverManager //localhost
                    .getConnection("jdbc:postgresql://localhost:5432/fabrisoft",
                    "postgres",
                    "postgres123");/**/
        } catch (ClassNotFoundException ex) {
            String Erro=ex.getMessage();
        } catch (SQLException ex) {
            String Erro=ex.getMessage();
        }
        return null;
    }
    /**Fecha a conexao
     * @param rs Resulstet
     * @param st Statement
     * @param conn Connection
     */
    public static void fechaTudo(ResultSet rs, Statement st, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Banco    .class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private static void atualizaBanco(){
        
    }
}