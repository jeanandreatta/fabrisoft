/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrisoft.controller;

import fabrisoft.model.Cidade;
import fabrisoft.util.language.Texto;
import fabrisoft.view.BaseForm;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;
import javax.swing.JComboBox;

/**
 *
 * @author Floripes
 */
public abstract class BaseController{
    private boolean chamou=false;
    /**
     * Carrega os eventos dentro do form
     * @param frm tipo deve ser da classe filha usado na controller
     */
    protected BaseController(BaseForm frm) throws NullPointerException{
        if(frm!=null)
            carregaEventos(frm);
        else
            throw new NullPointerException(Texto.FORMULARIO_NAO_DEFINIDO);
    }
            
    protected static void carregaCidadesPorUF(JComboBox comboUF,JComboBox comboCidades){
        String uf=comboUF.getSelectedItem().toString();
        comboCidades.removeAllItems();
        ArrayList<Cidade> cidades=Cidade.buscaSigla(uf);
        for(Cidade c:cidades)
           comboCidades.addItem(c);
    }
        
    protected static void setValorComboCidade(JComboBox comboCidade,String nomeCidade){
        int i;
        for( i=0;
            i<comboCidade.getItemCount() &&
            !((Cidade)comboCidade.getItemAt(i)).getNome().equals(nomeCidade)
            ;i++);
        comboCidade.setSelectedIndex(i);
    }
        
    @SuppressWarnings("empty-statement")
    protected static void setValorComboUF(JComboBox comboUF,String nomeUF){
        int i;
        for( i=0;
                i<comboUF.getItemCount() &&
                !comboUF.getItemAt(i).equals(nomeUF)
                ;i++);
        comboUF.setSelectedIndex(i);
    }
        
    public final void carregaEventos(BaseForm frm){
        frm.setTitle(Texto.TITULO);
        frm.getBtnAlterar().addActionListener(alterar());
        frm.getBtnNovo().addActionListener(novo());
        frm.getBtnCancelar().addActionListener(cancelar());
        frm.getBtnExcluir().addActionListener(excluir());
        frm.getBtnGravar().addActionListener(gravar());
        frm.getBtnProcurar().addActionListener(procurar());
        carregaEventos();
    }
    
    protected abstract ActionListener excluir();
    protected abstract ActionListener gravar();
    protected abstract ActionListener cancelar();
    protected abstract ActionListener alterar();
    protected abstract ActionListener procurar();
    protected abstract ActionListener novo();
    protected abstract void carregaEventos();
    
    
    
        
}
