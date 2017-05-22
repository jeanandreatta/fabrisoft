package fabrisoft.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import fabrisoft.sql.Banco;
import fabrisoft.util.Encripta;
import fabrisoft.util.Mensagem;

public class Usuario {

    public static boolean existeUsuario() {
        String sql="SELECT * from usuario where dt_exclusao is null";
            ResultSet rs=null;
            Usuario u= null;
            Connection conn=Banco.getConexao();
            PreparedStatement ps=null;
            try{
                ps=conn.prepareStatement(sql);
                rs = ps.executeQuery();
               if(rs.next())
                 return true;
               
            }catch(SQLException ex){
                Mensagem.atencao(ex.getMessage());
            }finally{
                Banco.fechaTudo(rs, ps, conn);
            }
            return  false;
    }

    public static boolean ultimo() {
          String sql="SELECT count(*)  as count from usuario where dt_exclusao is null";
            ResultSet rs=null;
            Usuario u= null;
            Connection conn=Banco.getConexao();
            PreparedStatement ps=null;
            try{
                ps=conn.prepareStatement(sql);
                rs = ps.executeQuery();
               if(rs.next())
                 return rs.getInt("count")==1;                   
            }catch(SQLException ex){
                Mensagem.atencao(ex.getMessage());
            }finally{
                Banco.fechaTudo(rs, ps, conn);
            }
            return  false;
    }
    
    public static boolean ultimoAdministrador() {
          String sql="SELECT count(*)  as count from usuario where dt_exclusao is null and tipo_usuario='"+ADMINISTRADOR+"'";
            ResultSet rs=null;
            Usuario u= null;
            Connection conn=Banco.getConexao();
            PreparedStatement ps=null;
            try{
                ps=conn.prepareStatement(sql);
                rs = ps.executeQuery();
               if(rs.next())
                 return rs.getInt("count")==1;                   
            }catch(SQLException ex){
                Mensagem.atencao(ex.getMessage());
            }finally{
                Banco.fechaTudo(rs, ps, conn);
            }
            return  false;
    }

    private String usuario;
    private String senha;
    private int codigo=0;
    private char tipo;
    private Date exclusao=null;
    private ArrayList <Formulario> formularios=new ArrayList<Formulario>();
    public static final char ADMINISTRADOR='A',USUARIO='U';
    public static Usuario doLogin(String usuario, String senha){
        Usuario u=usuarioExiste(usuario);
        if(u!=null&&u.getSenha().equals(Encripta.getHash(senha))){
            u.formularios=Formulario.consultaFormularios(u);
            return u;
        }
        return null;
    }
    public void addFormulario(Formulario f){
        this.formularios.add(f);
    }
    public boolean isAdministrador(){
        return getTipo()==ADMINISTRADOR; 
    }
    public boolean gravar(){
        String sql;
        if(this.codigo==0)
            sql="INSERT INTO usuario(" +
                "tipo_usuario,  usuario, senha)" +
                "VALUES (?, ?, ?);";
        else if(exclusao!=null)
            sql="UPDATE usuario SET dt_exclusao = ? WHERE codigo = ?;"; 
            else
                sql="UPDATE usuario SET tipo_usuario=?, senha=? WHERE codigo= ?;";
        
        Connection conn=Banco.getConexao();
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement(sql);
            if(this.codigo==0){ //inclusao
                ps.setString(1,""+tipo); //1  
                ps.setString(2, usuario);//2
                ps.setString(3,senha);//3
            }
            else if(exclusao!=null){//exclusao
                ps.setDate(1, new java.sql.Date (exclusao.getTime()));
                ps.setInt(2, codigo);
            }else{//alteracao
                ps.setString(1,""+tipo); //1  
                ps.setString(2,senha);//3
                ps.setInt(3, codigo);
            }                
            if (ps.executeUpdate() > 0) {
                if(codigo==0)
                    codigo=buscaCodigo();
                ps=null;
                sql="Delete from acesso where cod_usuario= ?";
                ps=conn.prepareStatement(sql);
                ps.setInt(1, codigo);
                ps.executeUpdate();
                
                for(Formulario item:formularios){
                    ps=null;
                    sql="INSERT INTO acesso(" +
                    "cod_usuario, cod_formulario, acesso, editar, excluir)" +
                    "VALUES (?, ?, ?, ?, ?);";
                    ps=conn.prepareStatement(sql);
                    ps.setInt(1, codigo);
                    ps.setInt(2, item.getCodigo());
                    ps.setString(3, item.getPerfil().acessarPermitido()?"S":"N");
                    ps.setString(4, item.getPerfil().editarPermitido()?"S":"N");
                    ps.setString(5, item.getPerfil().excluirPermitido()?"S":"N");
                    ps.executeUpdate();
                }
                formularios=Formulario.consultaFormularios(this);
                return true;
            }
        }catch(SQLException e){
            Mensagem.atencao(e.getMessage());
        }finally {
            Banco.fechaTudo(null,ps, conn);
        }
        return false;
    }

    public static Usuario usuarioExiste(String usuario){
        
            String sql="SELECT * from usuario where usuario=? and dt_exclusao is null";
            ResultSet rs=null;
            Usuario u= null;
            Connection conn=Banco.getConexao();
            PreparedStatement ps=null;
            try{
                ps=conn.prepareStatement(sql);
                ps.setString(1, usuario);
                rs = ps.executeQuery();
               if(rs.next())
                   u=new Usuario(rs.getInt("codigo"),rs.getString("usuario"), rs.getString("senha"),rs.getString("tipo_usuario").charAt(0));
               
            }catch(SQLException ex){
                Mensagem.atencao(ex.getMessage());
            }finally{
                Banco.fechaTudo(rs, ps, conn);
            }
            return  u;
    }
    
private  int buscaCodigo(){
            String sql="SELECT * from usuario where usuario=? and dt_exclusao is null";
            ResultSet rs=null;
            Usuario u= null;
            Connection conn=Banco.getConexao();
            PreparedStatement ps=null;
            try{
                ps=conn.prepareStatement(sql);
                ps.setString(1, usuario);
                rs = ps.executeQuery();
               if(rs.next())
                   return rs.getInt("codigo");
               
            }catch(SQLException ex){
                Mensagem.atencao(ex.getMessage());
            }finally{
                Banco.fechaTudo(rs, ps, conn);
            }
            return -1;
    }
    public static ArrayList<Usuario> buscaNome(String usuario){
        
            String sql="SELECT * from usuario  where dt_exclusao is null";
            if(usuario.length()>0)
                sql+=" and usuario like ?";
            ResultSet rs=null;
           ArrayList<Usuario> u= new ArrayList<>();
            Connection conn=Banco.getConexao();
            PreparedStatement ps=null;
            try{
                ps=conn.prepareStatement(sql);
                if(usuario.length()>0)
                    ps.setString(1, "%"+usuario+"%");
                rs = ps.executeQuery();
               while(rs.next()){
                   Usuario user=new Usuario(rs.getInt("codigo"),rs.getString("usuario"), rs.getString("senha"),rs.getString("tipo_usuario").charAt(0));
                   user.formularios=Formulario.consultaFormularios(user);
                   u.add(user);
               }
               
            }catch(SQLException ex){
                Mensagem.atencao(ex.getMessage());
            }finally{
                Banco.fechaTudo(rs, ps, conn);
            }
            return  u;
    }
    public Formulario getFormulario(String nome) {
        for(Formulario frm:formularios)
            if(frm.getNome().equals(nome))
                return frm;
        return null;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Date getExclusao() {
        return exclusao;
    }

    public void setExclusao(Date exclusao) {
        this.exclusao = exclusao;
    }
    
    public Usuario() {
    }
    public void novosFormularios() {
        this.formularios=new ArrayList<>();        
        
    }
    public Usuario(String usuario, String senha, char tipo) {
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
    }
    public Usuario(int codigo,String usuario, String senha, char tipo) {
        this.codigo=codigo;
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return usuario;
    }
    
}

