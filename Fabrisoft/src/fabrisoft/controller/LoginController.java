/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.controller;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.KeyStroke;
import fabrisoft.model.Usuario;
import fabrisoft.model.Sessao;
import fabrisoft.util.Mensagem;
import fabrisoft.util.language.Texto;
import fabrisoft.view.LoginView;

/**
 *
 * @author Jean
 */
public class LoginController {
    private LoginView frm;
    public void iniciar() {
        frm=new LoginView(null, true);
        carregaEventos(); 
        while(!Usuario.existeUsuario()&&Mensagem.confirmar(Texto.SEM_USUARIO_CADASTRADO)){
            UsuarioController u=new UsuarioController();
            u.iniciar2();
        }
        if(Usuario.existeUsuario())
            frm.setVisible(true);
    }

    public void carregaEventos() {
        frm.getRootPane().setDefaultButton(frm.getBtnEntrar());         
        frm.getBtnCancelar().addActionListener((ActionListener) e->{
            frm.dispose();
        }) ;
        frm.getBtnEntrar().addActionListener((ActionListener)e->{
            Usuario u=Usuario.doLogin(frm.getTxtUsuario().getText(),new String (frm.getTxtSenha().getPassword()));
            if(u!=null){
                Sessao.getInstance().setUsuario(u);
                frm.dispose();
            }else
                Mensagem.atencao("Usuário e/ou Senha inválido!!!\nVerifique.");
        });
    }
            
        
}
