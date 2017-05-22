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
    }
    public String getErroVirgula(){
        return this.erro;
    }
    public String getErroEmLinha(){
        return this.erro.replaceAll(", ", "\n");
    }
    public int getQuantidade(){
        return qtde;
    }
    public boolean isEmpty(){
        return qtde==0;
    }
}
