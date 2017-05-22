/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.model;

/**
 *
 * @author Jean
 */
public class PerfilAcesso {
    private boolean acessar,excluir,editar;
    
    public boolean acessarPermitido() {
        return acessar;
    }

    public boolean excluirPermitido() {
        return excluir;
    }

    public boolean editarPermitido() {
        return editar;
    }

    public void setAcessar(boolean acessar) {
        this.acessar = acessar;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public PerfilAcesso(boolean acessar, boolean editar, boolean excluir) {
        this.acessar = acessar;
        this.excluir = excluir;
        this.editar = editar;
    }
    
    public void gravar(Usuario user, Formulario frm){
        
    }
    public void buscar(Usuario user, Formulario frm){
        
    }
}
