package fabrisoft.view;

import javax.swing.JMenuItem;

/*
 * Copyright (C) 2016 Jean.
 */

/**
 *
 * @author Jean
 */
public class PrincipalView extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    
    public JMenuItem getItemUsuario() {
        return itemUsuario;
    }

    public JMenuItem getItemCadastroCliente() {
        return itemCadastroCliente;
    }

    public JMenuItem getItemContasReceber() {
        return itemContasReceber;
    }

    public JMenuItem getItemProducao() {
        return itemProducao;
    }
    
    public PrincipalView() {
        initComponents();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barraMenu = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        itemUsuario = new javax.swing.JMenuItem();
        menuCadastros = new javax.swing.JMenu();
        itemCadastroCliente = new javax.swing.JMenuItem();
        menuMovimentacoes = new javax.swing.JMenu();
        itemContasReceber = new javax.swing.JMenuItem();
        itemProducao = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIBAZ - Sistema de Controle de Bazar");

        menuArquivo.setText("Arquivo");

        itemUsuario.setText("Usuários");
        menuArquivo.add(itemUsuario);

        barraMenu.add(menuArquivo);

        menuCadastros.setText("Cadastros");

        itemCadastroCliente.setText("Cliente");
        itemCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCadastroClienteActionPerformed(evt);
            }
        });
        menuCadastros.add(itemCadastroCliente);

        barraMenu.add(menuCadastros);

        menuMovimentacoes.setText("Movimentações");

        itemContasReceber.setText("Contas a Receber");
        itemContasReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemContasReceberActionPerformed(evt);
            }
        });
        menuMovimentacoes.add(itemContasReceber);

        itemProducao.setText("Produção");
        itemProducao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProducaoActionPerformed(evt);
            }
        });
        menuMovimentacoes.add(itemProducao);

        barraMenu.add(menuMovimentacoes);

        menuRelatorios.setText("Relatórios");
        barraMenu.add(menuRelatorios);

        setJMenuBar(barraMenu);

        setSize(new java.awt.Dimension(1054, 678));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCadastroClienteActionPerformed


    }//GEN-LAST:event_itemCadastroClienteActionPerformed

    private void itemContasReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemContasReceberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemContasReceberActionPerformed

    private void itemProducaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProducaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemProducaoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenuItem itemCadastroCliente;
    private javax.swing.JMenuItem itemContasReceber;
    private javax.swing.JMenuItem itemProducao;
    private javax.swing.JMenuItem itemUsuario;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenu menuMovimentacoes;
    private javax.swing.JMenu menuRelatorios;
    // End of variables declaration//GEN-END:variables
}
