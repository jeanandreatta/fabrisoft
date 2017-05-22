/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft;

import fabrisoft.controller.PrincipalController;
import fabrisoft.controller.UsuarioController;
import fabrisoft.view.CadastroClienteView;

/**
 *
 * @author Jean
 */
public class Fabrisoft {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        cofinguraUI();
        PrincipalController pc=new PrincipalController();
        pc.iniciar();
    }
    public static void teste(){
        UsuarioController uc=new UsuarioController();
        uc.iniciar2();
    }
    public static void cofinguraUI(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
   
    
}
