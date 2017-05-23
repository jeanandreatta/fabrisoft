/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrisoft.util;

/**
 *
 * @author Floripes
 */
public class Erro {
    private String erro;
    private int qtde=0;
    public Erro(){
        erro="";
        qtde=0;
    }
    public void putErro(String erro){
        qtde++;
        this.erro+=this.erro.isEmpty()?erro:", "+erro;
    }/**
     * Retorna erros separados por vírgula
     * @return erro1, erro2 ...
     */
    public String getErroVirgula(){
        return this.erro;
    }
    /**
     * Retorna erro formatados com quebra de linha.
     * @return string erro1+\n+erro2 ...
     */
    public String getErroEmLinha(){
        return this.erro.replaceAll(", ", "\n");
    }
    public int getQuantidade(){
        return qtde;
    }
    
    /**
     * 
     * @return true se estiver sem erros 
     */
    public boolean isEmpty(){
        return qtde==0;
    }
}
