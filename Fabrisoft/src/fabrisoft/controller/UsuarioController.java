/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import fabrisoft.model.Formulario;
import fabrisoft.model.PerfilAcesso;
import fabrisoft.model.Usuario;
import fabrisoft.model.Sessao;
import fabrisoft.util.Encripta;
import fabrisoft.util.Mensagem;
import fabrisoft.view.BaseForm;
import fabrisoft.view.ConsultaBasicaView;
import fabrisoft.view.UsuarioView;
import java.awt.event.ActionListener;

/**
 *
 * @author Jean
 */
public class UsuarioController{
    UsuarioView frm;
    Usuario usuarioAtual=null;
    Formulario f=null;
    ConsultaBasicaView frmConsulta;
    private boolean primeiroUsuario=false;
    public void iniciar() {
        frm=new UsuarioView(null, true);
        f=Sessao.getInstance().getUsuario().getFormulario(frm.getNome());
        if(f!=null&&f.getPerfil().acessarPermitido())
        {
            carregaEventos();
            frm.setVisible(true);
        }else
            Mensagem.permissaoAcesso();
    }
    public void iniciar2() {
        frm=new UsuarioView(null, true);
        carregaEventos();
        primeiroUsuario=true;
        frm.setVisible(true);
    }
    @Override
    protected final void carregaEventos(){
        frm.cancelar();
        ArrayList<Formulario> formularios=Formulario.todosNome();
        DefaultTableModel model = (DefaultTableModel) frm.getTabelaFormularios().getModel();
        for(Formulario item:formularios){                       
            model.addRow(new Object[]{item,false,false,false});
        }
        
        frm.getTxtUsuario().setEnabled(false);
        frm.getTxtSenha().setEnabled(false);
        frm.getCbAdministrador().setEnabled(false);
        frm.getTabelaFormularios().setEnabled(false);
        frm.getCbAdministrador().addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean v=frm.getCbAdministrador().isSelected();
                frm.getTabelaFormularios().setEnabled(!frm.getCbAdministrador().isSelected());
                for(int i=0;i<frm.getTabelaFormularios().getRowCount();i++){
                    frm.getTabelaFormularios().getModel().setValueAt(v, i, 1);
                    frm.getTabelaFormularios().getModel().setValueAt(v, i, 2);
                    frm.getTabelaFormularios().getModel().setValueAt(v, i, 3);
                }
                    
            }
        });
        frm.getBtnNovo().addActionListener(e->{
            limpar();
            frm.getTxtUsuario().setEnabled(true);
            frm.getTxtSenha().setEnabled(true);
            frm.getCbAdministrador().setEnabled(true);
            frm.getTabelaFormularios().setEnabled(true);
            
            if(primeiroUsuario){
                frm.getCbAdministrador().doClick();
                frm.getCbAdministrador().setEnabled(false);
                frm.getTabelaFormularios().setEnabled(false);
            }
            
            frm.incluir();
        });
        frm.getBtnAlterar().addActionListener(e->{
            frm.getTxtUsuario().setEnabled(false);
            frm.getTxtSenha().setEnabled(true);
            frm.getCbAdministrador().setEnabled(true);
            frm.getTabelaFormularios().setEnabled(!frm.getCbAdministrador().isSelected());
            frm.alterar();
        });
        frm.getBtnCancelar().addActionListener(e->{
            if(frm.getAcao()=='I'){
                limpar();
                frm.cancelar();
            }else{
                frm.visualizar();
                preencheTela(null);
            }
        });
        frm.getBtnExcluir().addActionListener(e->{
            Usuario u=(Usuario)Sessao.getInstance().getRegistroAtual();
            Usuario logado=(Usuario)Sessao.getInstance().getUsuario();
            boolean excluir=false,estaLogado=false;
            if(logado.getFormulario(frm.getNome()).getPerfil().excluirPermitido()){
                estaLogado=logado.getCodigo()==u.getCodigo();
                if(estaLogado){//é o logado
                    if(Mensagem.confirmar("Se você excluir o seu registro o sistema será finalizado\nDeseja continuar?"))
                        excluir=true;
                    else
                        excluir=false;
                    
                    if(excluir==true)
                        if(logado.isAdministrador()&&logado.ultimoAdministrador()){//é administrador e é o último
                            excluir=false;
                            Mensagem.atencao("Erro ao excluir, você é o último administrador.");
                        }else                      
                            if(Usuario.ultimo())//é o último
                                if(Mensagem.confirmar("Você é o último usuário.\n"
                                    + "Após a exclusão será necessário criar outro usuário!"
                                    + "\nDeseja continuar?"))
                                    excluir=true;
                                else
                                    excluir=false;
                }else{//não é o logado
                    if(u.getTipo()=='A')//administrador
                        if(logado.getTipo()=='A')//logado também é adm
                            excluir=true;
                        else//logado não é adm
                            Mensagem.atencao("Só um administrador pode excluir outro administrador!");
                    else//não é administrador
                        excluir=true;
                }
            }else//sem permissao
                Mensagem.permissaoExcluir();
            if(excluir&&Mensagem.confirmaExclusao()){
                u.setExclusao(new Date());
                u.gravar();
                if(estaLogado)
                    System.exit(0);
                Sessao.getInstance().setRegistroAtual(null);
                frm.cancelar();
                limpar();
            }
        });
        
        gravar();
        buscar();
    }
    private void preencheTela(Usuario u){
        if(u!=null)
            Sessao.getInstance().setRegistroAtual(u);
        else
            u=(Usuario)Sessao.getInstance().getRegistroAtual();
        if(u!=null){
            frm.getTxtUsuario().setEnabled(false);
            frm.getTxtSenha().setEnabled(false);
            frm.getCbAdministrador().setEnabled(false);
            frm.getTabelaFormularios().setEnabled(false);
            DefaultTableModel model = (DefaultTableModel) frm.getTabelaFormularios().getModel();
            frm.getTxtUsuario().setText(u.getUsuario());
            frm.getTxtSenha().setText(u.getSenha());
            frm.getCbAdministrador().setSelected(u.getTipo()=='A');
            if(frm.getAcao()=='E')
                frm.getTabelaFormularios().setEnabled(!frm.getCbAdministrador().isSelected());
            else
                frm.getTabelaFormularios().setEnabled(false);
            for(int i=0;i<model.getRowCount();i++){
                Formulario f=u.getFormulario(model.getValueAt(i,0).toString());
                if(f!=null){
                    model.setValueAt(f.getPerfil().acessarPermitido(), i,1);
                    model.setValueAt(f.getPerfil().editarPermitido(), i,2);
                    model.setValueAt(f.getPerfil().excluirPermitido(), i,3);
                }
            }
        }else{
            limpar();
            frm.cancelar();
        }
    }
    @Override
    protected ActionListener gravar(){
        DefaultTableModel model = (DefaultTableModel) frm.getTabelaFormularios().getModel();
        return e->{
            Usuario usuarioGravar=new Usuario(frm.getTxtUsuario().getText(), 
                Encripta.getHash(new String(frm.getTxtSenha().getPassword())), 
                frm.getCbAdministrador().isSelected()?Usuario.ADMINISTRADOR:Usuario.USUARIO
            );
            boolean gravar=false;
            
            //se for primeiro usuario tem que ser administrador
            if(primeiroUsuario){
               if(frm.getCbAdministrador().isSelected())
                   gravar=true;
            }else{
                Usuario logado=(Usuario)Sessao.getInstance().getUsuario();
                if(logado.getFormulario(frm.getNome()).getPerfil().editarPermitido())
                    
                    if(frm.getAcao()==BaseForm.INCLUINDO){
                        if(usuarioGravar.isAdministrador()){
                            if(logado.isAdministrador())
                                gravar=true;
                        }else
                            gravar=true;
                    }else{//Alterando
                    
                        if(usuarioGravar.isAdministrador()||usuarioAtual.isAdministrador()){
                            if(logado.isAdministrador())
                                if(!usuarioAtual.isAdministrador()||!usuarioAtual.ultimoAdministrador())//se antes nao era administrador
                                    gravar=true;
                                else
                                    if() //nao é o ultimo administrador
                                        gravar=true;
                        }else//gravar nem atual é administrador
                            gravar=true;
                        
                   }
                   
               else
                   Mensagem.permissaoGravar();
            }
            
            
            
            
            
            
            
            if(primeiroUsuario||Sessao.getInstance().getUsuario().getFormulario(frm.getNome()).getPerfil().editarPermitido()){
                if(frm.getAcao()==BaseForm.INCLUINDO){
//                    Usuario u=new Usuario(frm.getTxtUsuario().getText(),Encripta.getHash(new String(frm.getTxtSenha().getPassword())), 
//                            frm.getCbAdministrador().isSelected()?Usuario.ADMINISTRADOR:Usuario.USUARIO);;

                    if((primeiroUsuario&&frm.getCbAdministrador().isSelected())||!primeiroUsuario)
                        if(Usuario.usuarioExiste(u.getUsuario())==null)
                            if(u.getUsuario().length()>0 && u.getSenha().length()>0){
                                for(int i=0;i<model.getRowCount();i++){
                                    Formulario nf=new Formulario(((Formulario)model.getValueAt(i, 0)).getCodigo(), 
                                            ((Formulario)model.getValueAt(i, 0)).getNome());
                                    
                                    nf.setPerfil(new PerfilAcesso(Boolean.parseBoolean(model.getValueAt(i,1).toString()),
                                            Boolean.parseBoolean(model.getValueAt(i,2).toString()), 
                                            Boolean.parseBoolean(model.getValueAt(i,3).toString())));
                                    u.addFormulario(nf);
                                }
                                gravar=false;
                                if(!primeiroUsuario){
                                    
                                    if(u.isAdministrador())
                                        if(logado.isAdministrador())
                                            if(logado.ultimoAdministrador()){
                                                gravar=true;
                                            }else
                                                Mensagem.atencao("Você é útimo administrador! Alteração não permitida!");
                                        else
                                           Mensagem.atencao("Somente administrador cria Administrador!");
                                    else
                                        gravar=true;
                                }else
                                    gravar=true;

                                if(gravar&&u.gravar()){
                                    if(!primeiroUsuario){

                                        preencheTela(u);
                                        frm.visualizar();  
                                    }else{
                                        Mensagem.atencao("Usuário cadastrado com sucesso\n"
                                                + "Na próxima tela preencha com o usuário e senha cadastrados!");
                                        frm.dispose();
                                    }
                                }else{
                                    Mensagem.atencao("Ocorreu um erro ao gravar usuário. Tente novamente!");
                                    frm.cancelar();
                                }
                            }else
                                Mensagem.atencao("Usuario e/ou senha com tamanho curto demais!");
                        else
                            Mensagem.atencao("Usuário já cadastrado!");
                    else
                        Mensagem.atencao("Nenhum usuário cadastrado!\nO primeiro usuário tem que ser do tipo administrador!");
                }else{//ALTERACAO
                    Usuario u=(Usuario)Sessao.getInstance().getRegistroAtual();
                    Usuario logado=(Usuario)Sessao.getInstance().getUsuario();
                    boolean gravar=false;
                    if(u.getTipo()=='A')//é administrador
                        if(logado.getTipo()=='A')
                            gravar=true;
                        else
                            Mensagem.atencao("Somente administrador pode editar administrador!");
                    else //não é o administrador
                        if(frm.getCbAdministrador().isSelected())
                            if(logado.getTipo()=='A')
                                gravar=true;
                            else
                                Mensagem.atencao("Somente administrador pode criar outro administrador!");
                        else{
                            boolean estaLogado=logado.getCodigo()==u.getCodigo();
                            if(estaLogado){//é o logado
                                if(!Usuario.ultimoAdministrador())//não é o último administrador
                                        gravar=true;
                                else
                                    Mensagem.atencao("Você é o último administrador, alteração não permitida!");
                            }else//não é o logado
                                gravar=true;
                        }
                    if(gravar){
                        if(!u.getSenha().equals(new String(frm.getTxtSenha().getPassword())))//senha não alterada               
                            u.setSenha(Encripta.getHash(new String(frm.getTxtSenha().getPassword())));
                        u.setTipo(frm.getCbAdministrador().isSelected()?'A':'U');
                        u.novosFormularios();
                        if(u.getSenha().length()>0){
                                for(int i=0;i<model.getRowCount();i++){
                                    Formulario nf=new Formulario(((Formulario)model.getValueAt(i, 0)).getCodigo(), 
                                            ((Formulario)model.getValueAt(i, 0)).getNome());
                                    nf.setPerfil(new PerfilAcesso(Boolean.parseBoolean(model.getValueAt(i,1).toString()),
                                            Boolean.parseBoolean(model.getValueAt(i,2).toString()), 
                                            Boolean.parseBoolean(model.getValueAt(i,3).toString())));
                                    u.addFormulario(nf);
                                }
                                if(u.gravar()){
                                    if(u.getCodigo()==Sessao.getInstance().getUsuario().getCodigo())
                                        Sessao.getInstance().setUsuario(u);
                                    preencheTela(u);
                                    frm.visualizar();  
                                }else{
                                    Mensagem.atencao("Ocorreu um erro ao gravar usuário. Tente novamente!");
                                    frm.cancelar();
                                }
                        }else
                            Mensagem.atencao("Senha com tamanho curto demais!");
                    }    
                }
            }else//sem permissão
                Mensagem.permissaoGravar();

        };
   }
   private void buscar(){
        frm.getBtnProcurar().addActionListener(e->{
            frmConsulta=new ConsultaBasicaView(null, true);
            DefaultTableModel modeloConsulta = (DefaultTableModel) frmConsulta.getTabelaConsulta().getModel();
            modeloConsulta.addColumn("Codigo");
            modeloConsulta.addColumn("Usuário");
            frmConsulta.getBtnConsulta().addActionListener(evt->{
                modeloConsulta.setRowCount(0);
                ArrayList<Usuario> arr=Usuario.buscaNome(frmConsulta.getTxtProcura().getText());
                for(Usuario item:arr){                       
                    modeloConsulta.addRow(new Object[]{item.getCodigo(),item});
                }
                frmConsulta.getTabelaConsulta().addMouseListener(new MouseAdapter(){
                    public void mousePressed(MouseEvent me){
                        if (me.getClickCount() == 2) {
                            int row=frmConsulta.getTabelaConsulta().getSelectedRow();
                            if(row>-1){
                                Sessao.getInstance().setResultadoConsulta(frmConsulta.getTabelaConsulta().getValueAt(row, 1));
                            }
                            frmConsulta.dispose();
                        }
                    }
                });
            });
            frmConsulta.setVisible(true);
            Sessao.getInstance().setRegistroAtual(null);
            Usuario u=(Usuario)Sessao.getInstance().getResultadoConsulta();
            if(u!=null){
                preencheTela(u);
                frm.visualizar();
                usuarioAtual=u;
            }else{
                preencheTela(u);
                frm.cancelar();
            }
        });
   }

    private void limpar() {
        frm.getTxtUsuario().setText("");
        frm.getTxtUsuario().setEnabled(false);
        frm.getTxtSenha().setEnabled(false);
        frm.getTxtSenha().setText("");
        frm.getCbAdministrador().setEnabled(false);
        frm.getCbAdministrador().setSelected(false);
        frm.getTabelaFormularios().setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) frm.getTabelaFormularios().getModel();
        for(int i=0;i<model.getRowCount();i++){
            model.setValueAt(false, i,1);
            model.setValueAt(false, i,2);
            model.setValueAt(false, i,3);
        }
    }
}
