/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrisoft.controller.interfacevalidator;

import fabrisoft.model.Sessao;
import fabrisoft.view.BaseForm;

/**
 *
 * @author Floripes
 */
public class VerificaPermissao {
    public static boolean gravarPermitido(BaseForm frm){
        return Sessao.getInstance().getUsuario().getFormulario(frm.getNome()).getPerfil().editarPermitido();
    }
    public static boolean acessarPermitido(BaseForm frm){
        return Sessao.getInstance().getUsuario().getFormulario(frm.getNome()).getPerfil().acessarPermitido();
    }
    public static boolean excluirPermitido(BaseForm frm){
        return Sessao.getInstance().getUsuario().getFormulario(frm.getNome()).getPerfil().excluirPermitido();
    }
}
