/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import fabrisoft.sql.Banco;
import fabrisoft.util.Mensagem;

/**
 *
 * @author Jean
 */
public class Formulario {
    private int codigo;
    private String nome;
    private PerfilAcesso perfil;

    public static ArrayList <Formulario> consultaFormularios(Usuario usuario){
        ArrayList <Formulario> f=new ArrayList<>();
         String sql="SELECT * from acesso where cod_usuario=?";
            ResultSet rs=null,rs2;
            Usuario u= null;
            Connection conn=Banco.getConexao();
            PreparedStatement ps=null;
            try{
                ps=conn.prepareStatement(sql);
                ps.setInt(1, usuario.getCodigo());
                rs = ps.executeQuery();
                while(rs.next()){
                    PerfilAcesso p=new PerfilAcesso(rs.getString("acesso").charAt(0)=='S', 
                            rs.getString("editar").charAt(0)=='S', 
                            rs.getString("excluir").charAt(0)=='S');
                    ps=null;
                    sql="select * from formularios  where codigo =?";
                    ps=conn.prepareStatement(sql);
                    ps.setInt(1, rs.getInt("cod_formulario"));
                    rs2 = ps.executeQuery();
                    while(rs2.next())
                        f.add(new Formulario(rs2.getInt("codigo"), rs2.getString("nome"),p));
                }
                    
            }catch(SQLException ex){
                Mensagem.atencao(ex.getMessage());
            }finally{
                Banco.fechaTudo(rs, ps, conn);
            }
            return f;
    }
    
    public static ArrayList <Formulario> todosNome(){
        ArrayList <Formulario> f=new ArrayList<>();
        String sql="SELECT * from formularios";
            ResultSet rs=null;
            Usuario u= null;
            Connection conn=Banco.getConexao();
            PreparedStatement ps=null;
            try{
                ps=conn.prepareStatement(sql);
                rs = ps.executeQuery();
               while(rs.next())
                   f.add(new Formulario(rs.getInt("codigo"),rs.getString("nome")));
               
            }catch(SQLException ex){
            }finally{
                Banco.fechaTudo(rs, ps, conn);
            }
            return  f;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PerfilAcesso getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilAcesso perfil) {
        this.perfil = perfil;
    }

    public Formulario(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Formulario(int codigo, String nome, PerfilAcesso perfil) {
        this.codigo = codigo;
        this.nome = nome;
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return  nome;
    }
    private boolean trocadeAdministrador(){
        String sql="SELECT * from usuario where codigo= ? and tipo_usuario='A' and dt_exclusao is null";
        ResultSet rs=null;
        Usuario u= null;
        Connection conn=Banco.getConexao();
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
           if(rs.next()){
               sql="SELECT * from usuario where codigo<> ? and tipo_usuario='A' and dt_exclusao is null";
               ps=conn.prepareStatement(sql);
               ps.setInt(1, codigo);
               rs = ps.executeQuery();
               if(rs.next())
                   return true;
           }
               

        }catch(SQLException ex){
        }finally{
            Banco.fechaTudo(rs, ps, conn);
        }
        return  false;
    }
    
}
