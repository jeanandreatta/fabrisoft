/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrisoft.controller;

import fabrisoft.model.Formulario;
import fabrisoft.model.Sessao;
import fabrisoft.util.Mensagem;
import fabrisoft.util.language.Texto;
import fabrisoft.view.BaseForm;
import fabrisoft.view.ProducaoView;
import java.awt.event.ActionListener;

/**
 *
 * @author Floripes
 */
public class ProducaoController extends BaseController{
    private static ProducaoView frm;

    public ProducaoController(BaseForm frm) {
        super(frm);
        if(frm instanceof ProducaoView)
            frm=(ProducaoView)frm;
        else
            throw new IllegalArgumentException(Texto.FORMULARIO_NAO_DEFINIDO);
        
        Formulario f=Sessao.getInstance().getUsuario().getFormulario(frm.getNome());
        if(f!=null&&f.getPerfil().acessarPermitido()){
            frm.getBtnCancelar().doClick();
            frm.setVisible(true);
        }else
            Mensagem.permissaoAcesso();
    }

    @Override
    protected ActionListener excluir() {
        return e -> {};
    }

    @Override
    protected ActionListener gravar() {
        return e -> {};
    }

    @Override
    protected ActionListener cancelar() {
        return e -> {};
    }

    @Override
    protected ActionListener alterar() {
        return e -> {};
    }

    @Override
    protected ActionListener procurar() {
        return e -> {};
    }

    @Override
    protected ActionListener novo() {
        return e -> {};
    }

    @Override
    protected void carregaEventos() {
         
    }
    
    public static void inicializar(){
        frm=new ProducaoView(null, true);
        new ProducaoController(frm);
        frm=null;
        System.gc();
    }
}
