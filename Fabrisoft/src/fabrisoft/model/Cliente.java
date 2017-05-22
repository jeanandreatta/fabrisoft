/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import fabrisoft.sql.Banco;
import fabrisoft.util.Erro;
import fabrisoft.util.Mascara;
import fabrisoft.util.Mensagem;
import fabrisoft.util.Validador;

/**
 *
 * @author Jean
 */
public class Cliente {
    private int codigo; 
    private String nome,documento, endereco,numero,bairro,cep,sexo,email,telefone,tipo ;
    private Cidade cidade;
    private Date exclusao;
    public static final int MAX_NOME=200,MAX_ENDERECO=200,MAX_NUMERO=10,MAX_BAIRRO=60,MAX_EMAIL=200;
    public static final int MIN_NOME=2,MIN_ENDERECO=3,MIN_NUMERO=1,MIN_BAIRRO=2;
    
    public boolean excluir(){
        String sql="UPDATE cliente SET dt_exclusao=? where codigo=?";
        Connection conn = Banco.getConexao();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date (exclusao.getTime()));
            ps.setInt(2, codigo);
            return ps.executeUpdate()>0;
        }catch(SQLException ex){
            Mensagem.atencao(ex.getMessage());
        }finally{
            Banco.fechaTudo(rs, ps, conn);
        }
        return false;
    }
    public boolean salvar(){
     String sql="";
     if(codigo==0)
         sql="INSERT INTO CLIENTE(nome,documento, endereco,end_numero,bairro,cep,sexo,email,telefone,tipo,cod_cidade) "
             + "values(?,?, ?,?,?,?,?,?,?,?,?) returning codigo";
     else
        sql="UPDATE cliente SET nome=?, documento=?, endereco=?, end_numero=?, "
                +" bairro=?, cep=?, sexo=?, email=?, telefone=?, tipo=?,cod_cidade=? WHERE codigo=?;";
        Connection conn = Banco.getConexao();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement(sql);

            ps.setString(1, nome);
            ps.setString(2, documento);
            ps.setString(3, endereco);
            ps.setString(4, numero);
            ps.setString(5, bairro);
            ps.setString(6, cep);
            ps.setString(7, sexo);
            ps.setString(8, email);
            ps.setString(9, telefone);
            ps.setString(10, tipo);
            ps.setInt(11, cidade.getCodigo());
         
            if(codigo!=0){
                ps.setInt(12,codigo);
                return ps.executeUpdate()>0;
            }else{
                rs=ps.executeQuery();
                if(rs.next()){
                    codigo=rs.getInt("codigo");
                    return true;
                }
            }
    
        }catch(SQLException ex){
            Mensagem.atencao(ex.getMessage());
        }finally{
            Banco.fechaTudo(rs, ps, conn);
        }
     return false;
 }
 public static ArrayList<Cliente> buscaNome(String cliente){
        
            String sql="SELECT * from cliente  where dt_exclusao is null";
            if(cliente.length()>0)
                sql+=" and nome like ?";
            ResultSet rs=null;
            ArrayList<Cliente> r= new ArrayList<>();
            Cliente c=null;
            Connection conn=Banco.getConexao();
            PreparedStatement ps=null;
            try{
                ps=conn.prepareStatement(sql);
                if(cliente.length()>0)
                    ps.setString(1, "%"+cliente+"%");
                rs = ps.executeQuery();
               while(rs.next()){
                   c=new Cliente(rs.getInt("codigo"),rs.getString("nome"),rs.getString("documento"),rs.getString("endereco"),
                           rs.getString("end_numero"),rs.getString("bairro"),rs.getString("cep"),rs.getString("sexo"),rs.getString("email"),rs.getString("telefone"),rs.getString("tipo"),Cidade.buscaCodigo(rs.getInt("cod_cidade")));
                   r.add(c);
               }
               
            }catch(SQLException ex){
                Mensagem.atencao(ex.getMessage());
            }finally{
                Banco.fechaTudo(rs, ps, conn);
            }
            return  r;
    }
    public Cliente(int codigo, String nome, String documento, String endereco, String numero, String bairro, String cep, String sexo, String email, String telefone, String tipo, Cidade cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.documento = Mascara.removeFormatacao(documento);
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = Mascara.removeFormatacao(cep);
        this.sexo = sexo;
        this.email = email;
        this.telefone = Mascara.removeFormatacao(telefone);
        this.tipo = tipo;
        this.cidade = cidade;
    }
    /***
     * 
     * @return String com erros se estiver vazio é porque foi validado
     */
    public Erro validar(){
        Erro erro=new Erro();
        if(nome.length()<1) erro.putErro("Nome muito curto");
        if(tipo.equals("J")){
            if(!Validador.isValidCNPJ(documento))
                erro.putErro("CNPJ inválido!");
        }else{
            if(!Validador.isValidCPF(documento))
                erro.putErro("CPF inválido!");
        }
        if(endereco.length()<MIN_ENDERECO) erro.putErro("Endereco muito curto.");
        if(endereco.length()>MAX_ENDERECO) erro.putErro("Endereco muito grande.");
        
        if(numero.length()<MIN_NUMERO) erro.putErro("Número muito curto.");
        if(numero.length()>MAX_NUMERO) erro.putErro("Número muito grande.");
        
        if(bairro.length()<MIN_BAIRRO)erro.putErro("Bairro muito curto.");
        if(bairro.length()>MAX_BAIRRO)erro.putErro("Bairro muito grande.");
        
        if(cidade==null) erro.putErro("Escolha uma cidade!");
        
        if(cep.length()!=8)erro.putErro("CEP inválido.");
        if(telefone.length()>0)
            if(telefone.length()!=10&&telefone.length()!=11)
                erro.putErro("Telefone inválido.");
        if(!Validador.isValidEmail(email))
            erro.putErro("E-mail inválido.");
        if(email.length()>MAX_EMAIL)
            erro.putErro("E-mail muito grande.");
        return erro;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public Date getExclusao() {
        return exclusao;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void setExclusao(Date exclusao) {
        this.exclusao = exclusao;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }
}
