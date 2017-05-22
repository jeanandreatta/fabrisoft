/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.model;

/**
 *
 * @author Jean
 */
public class Sessao {
    private static Sessao instance = null;
    private Usuario usuario=null;
    private String formulario;
    private Object resultadoConsulta=null;
    private Object registroAtual=null;

    public Object getRegistroAtual() {
        return registroAtual;
    }

    public void setRegistroAtual(Object registroAtual) {
        this.registroAtual = registroAtual;
    }
    
    public Object getResultadoConsulta() {
        Object r=resultadoConsulta;
        resultadoConsulta=null;
        return r;
    }

    public void setResultadoConsulta(Object resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }
    
    private Sessao(){
    }
    public void setUsuario(Usuario usuario){
       this.usuario = usuario;
    }
    public Usuario getUsuario(){
        return usuario;
    }
    public static Sessao getInstance(){
          if(instance == null){
                instance = new Sessao();
          }
         return instance;
    }
}
