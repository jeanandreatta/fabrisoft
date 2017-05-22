/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import fabrisoft.model.Sessao;
import fabrisoft.sql.Banco;
import fabrisoft.util.Mensagem;
import fabrisoft.util.language.Texto;
import fabrisoft.view.PrincipalView;
import fabrisoft.view.ProducaoView;

/**
 *
 * @author Jean
 */
public class PrincipalController {
    PrincipalView frm;
    public void iniciar(){
        Connection conn=Banco.getConexao();
        if(conn!=null)
        {  
            Banco.fechaTudo(null, null, conn);
            LoginController lc=new LoginController();
            boolean fechar;
            lc.iniciar();
            if(Sessao.getInstance().getUsuario()!=null){
                frm=new PrincipalView();
                this.carregaEventos();
                frm.setVisible(true);
            }
        }else
            Mensagem.atencao(Texto.ERRO_CONEXAO);
    }

    private void carregaEventos() {
        frm.getItemCadastroCliente().addActionListener(abrirCadastroCliente());
        frm.getItemContasReceber().addActionListener(abrirContasReceber());
        frm.getItemProducao().addActionListener(abrirProducao());
        frm.getItemUsuario().addActionListener(abrirUsuario());
        
    }

    private ActionListener abrirCadastroCliente() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroClienteController.inicializar();
            }
        };
    }

    private ActionListener abrirContasPagar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }

    private ActionListener abrirContasReceber() {
             return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
    }

    private ActionListener abrirProducao() {
            return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ProducaoController f=new ProducaoController(new ProducaoView(frm,true));
            }
        };
    }

    private ActionListener abrirCompra() {
            return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frm,"Ainda não suportado!");
            }
        };
    }

    private ActionListener abrirUsuario() {
           return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioController f=new UsuarioController();
                f.iniciar();
            }
        };
    }
    public static void configUI(){
        try {
            
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
