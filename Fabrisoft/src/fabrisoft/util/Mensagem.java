/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.util;

import fabrisoft.util.language.Texto;
import javax.swing.JOptionPane;

/**
 *
 * @author Jean
 */
public class Mensagem {
        
    public static void atencao(String msg){
        JOptionPane.showMessageDialog(null, msg,Texto.MENSAGEM_ATENCAO, JOptionPane.WARNING_MESSAGE,null);
    }
    private static void permissao(String msg){
        JOptionPane.showMessageDialog(null, msg,Texto.MENSAGEM_SEM_PERMISSAO, JOptionPane.ERROR_MESSAGE,null);
    }
    public static void permissaoAcesso(){
        permissao(Texto.MENSAGEM_ACESSAR_NAO_PERMITIDO);
    }
    public static void permissaoGravar(){
        permissao(Texto.MENSAGEM_GRAVAR_NAO_PERMITIDO);
    }
    public static void permissaoExcluir(){
        permissao(Texto.MENSAGEM_EXCLUIR_NAO_PERMITIDO);
    }
    public static void sucesso(){
               JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!!!","Sucesso!!!", 
                       JOptionPane.INFORMATION_MESSAGE,null);
    }
    public static boolean confirmar(String msg){
        return JOptionPane.showOptionDialog(null,msg,"Selecione uma opção!", 
      JOptionPane.YES_NO_OPTION, 
      JOptionPane.QUESTION_MESSAGE, 
      null, null, null)==JOptionPane.YES_OPTION;
    }
    public static boolean confirmaExclusao(){
        return Mensagem.confirmar("Excluir registro?");
    }
    
}
