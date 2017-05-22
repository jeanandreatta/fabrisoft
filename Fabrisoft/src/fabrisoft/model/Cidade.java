/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fabrisoft.sql.Banco;
import fabrisoft.util.Mensagem;
import java.util.ArrayList;

/**
 *
 * @author Jean
 */
public class Cidade {
    private int codigo;
    private String nome;
    private String uf;
 public static ArrayList<Cidade> buscaSigla(String sigla) {
            ArrayList<Cidade> lista=new ArrayList<>();
            String sql="SELECT * from cidade where sigla=?";
            ResultSet rs=null;
            Usuario u= null;
            Connection conn=Banco.getConexao();
            PreparedStatement ps=null;
            try{
                ps=conn.prepareStatement(sql);
                ps.setString(1, sigla);
                rs = ps.executeQuery();
               while(rs.next())
                   lista.add(new Cidade(rs.getInt("codigo"),rs.getString("nome"),rs.getString("sigla")));
               
            }catch(SQLException ex){
                Mensagem.atencao(ex.getMessage());
            }finally{
                Banco.fechaTudo(rs, ps, conn);
            }
            return lista;
    }
 
    public static Cidade buscaCodigo(int codigo) {
            String sql="SELECT * from cidade where codigo=?";
            ResultSet rs=null;
            Usuario u= null;
            Connection conn=Banco.getConexao();
            PreparedStatement ps=null;
            try{
                ps=conn.prepareStatement(sql);
                ps.setInt(1, codigo);
                rs = ps.executeQuery();
               if(rs.next())
                   return new Cidade(rs.getInt("codigo"),rs.getString("nome"),rs.getString("sigla"));
               
            }catch(SQLException ex){
                Mensagem.atencao(ex.getMessage());
            }finally{
                Banco.fechaTudo(rs, ps, conn);
            }
            return null;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cidade(int codigo, String nome,String uf) {
        this.codigo = codigo;
        this.nome = nome;
        this.uf=uf;
    }

    public String getUF() {
        return uf;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}
