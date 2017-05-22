/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.controller;

import fabrisoft.util.Botoes;
import fabrisoft.view.VendaView;

/**
 *
 * @author Jean
 */
public class VendaController {
    private VendaView frm;
    public void iniciar(){
        frm=new VendaView(null, true);
        carregaEnventos();
        frm.getBtnCancelar().doClick();
        frm.setVisible(true);
    }

    private void carregaEnventos() {
       
        
    }
}
