/*
 * Copyright (C) 2017 Jean.
 */

package fabrisoft.model;

import java.io.File;
import fabrisoft.util.Mensagem;

/**
 *
 * @author Jean
 */
public class Configuracao {
    /*
    public String getCaminhoImagens() {
        String sql="Select caminho_imagens from configuracao";
        Connection conn=Conexao.getConexao();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement(sql);
            ps.executeQuery();
            rs.next();
            rs.getString("caminho_imagens");
        }catch(SQLException e){
        }finally {
            Conexao.fechaTudo(null,ps, conn);
        }
        return null;
    }

    public String getCaminhoSons() {
        String sql="Select caminho_sons from configuracao";
        Connection conn=Conexao.getConexao();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement(sql);
            ps.executeQuery();
            rs.next();
            rs.getString("caminho_sons");
        }catch(SQLException e){
        }finally {
            Conexao.fechaTudo(null,ps, conn);
        }
        return null;
    }*/
    public String getCaminhoSons() {
        Mensagem.atencao(new File(".").getAbsolutePath());
        return "";
    }
}
