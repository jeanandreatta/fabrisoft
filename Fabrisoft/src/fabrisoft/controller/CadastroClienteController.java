/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.controller;

import fabrisoft.controller.interfacevalidator.InterfaceValidator;
import fabrisoft.controller.interfacevalidator.VerificaPermissao;
import fabrisoft.model.Cidade;
import fabrisoft.model.Formulario;
import fabrisoft.model.Cliente;
import fabrisoft.model.Sessao;
import fabrisoft.util.Erro;
import fabrisoft.util.ErroException;
import fabrisoft.util.Mascara;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import fabrisoft.util.Mensagem;
import fabrisoft.util.Validador;
import fabrisoft.util.language.Texto;
import fabrisoft.view.BaseForm;
import fabrisoft.view.CadastroClienteView;
import fabrisoft.view.ConsultaBasicaView;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.util.Date;

/**
 *
 * @author Jean
 */
public class CadastroClienteController extends BaseController {
    private static CadastroClienteView frm;
    private ConsultaBasicaView frmConsulta;
    private Cliente clienteAtivo=null;

    public CadastroClienteController(BaseForm frm) throws IllegalArgumentException {
        super(frm);
        if(frm instanceof CadastroClienteView)
            this.frm=(CadastroClienteView)frm;
        else
            throw new IllegalArgumentException(Texto.ERRO_FORM_ERRADO);
        
        Formulario f=Sessao.getInstance().getUsuario().getFormulario(frm.getNome());
        if(f!=null&&f.getPerfil().acessarPermitido()){
            frm.getBtnCancelar().doClick();
            frm.setVisible(true);
        }else
            Mensagem.permissaoAcesso();
    }
    
    private void validaTelefone() {
        if(frm.getAcao()!=BaseForm.VISUALIZANDO){
            try {
                InterfaceValidator.telefone(frm.getTxtTelefone());
            } catch (ErroException ex) {
                Mensagem.atencao(ex.getMessage());
            }
        }
    }
    
    private void validaDocumento(){
        if(frm.getAcao()!=BaseForm.VISUALIZANDO){
            frm.getTxtDocumento().setText(Mascara.removeFormatacao(frm.getTxtDocumento().getText()));
            if(frm.getTxtTipoPessoa().getSelectedItem().toString().equals("Física")){
                if(!Validador.isValidCPF(frm.getTxtDocumento().getText())){
                    Mensagem.atencao("CPF invalido");
                    InterfaceValidator.installErro(frm.getTxtDocumento());
                }else{
                    frm.getTxtDocumento().setText(Mascara.formata("###.###.###-##", frm.getTxtDocumento().getText()));
                    InterfaceValidator.installSucesso(frm.getTxtDocumento()); 
                }
            }else
                if(!Validador.isValidCNPJ(frm.getTxtDocumento().getText())&&frm.getAcao()!='V'){
                    Mensagem.atencao("CNPJ invalido");
                    InterfaceValidator.installErro(frm.getTxtDocumento());
                }else{                  
                    InterfaceValidator.installSucesso(frm.getTxtDocumento()); 
                    frm.getTxtDocumento().setText(Mascara.formata("##.###.###/####-##", frm.getTxtDocumento().getText()));
                }
        }
    }
    
    private void validaCEP(){
        if(frm.getAcao()!=BaseForm.VISUALIZANDO){
            try{
                InterfaceValidator.cep(frm.getTxtCEP());
            }catch(ErroException ex){
                Mensagem.atencao(ex.getMessage());
            }
        }
    }
    
    
    protected void carregaEventos() { 
       InterfaceValidator.addTamanhoTextField(frm.getTxtEndereco(), Cliente.MIN_ENDERECO, Cliente.MAX_ENDERECO);
       InterfaceValidator.addTamanhoTextField(frm.getTxtBairro(), Cliente.MIN_BAIRRO, Cliente.MAX_BAIRRO);
       InterfaceValidator.addTamanhoTextField(frm.getTxtNome(), Cliente.MIN_NOME, Cliente.MAX_NOME);
       InterfaceValidator.addTamanhoTextField(frm.getTxtNumero(), Cliente.MIN_NUMERO, Cliente.MAX_NUMERO);
             
       
       frm.getTxtTipoPessoa().addItemListener((ItemEvent e) -> {
            if(e.getStateChange()==ItemEvent.SELECTED)
                validaDocumento();
        });

        frm.getTxtDocumento().addFocusListener((new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                
            }
            @Override
            public void focusLost(FocusEvent e) {
                validaDocumento();
            }
        }));        
       
        frm.getTxtTelefone().addFocusListener((new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                
            }
            @Override
            public void focusLost(FocusEvent e) {
                validaTelefone();
            }
        }));  
       
        frm.getTxtCEP().addFocusListener((new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                
            }
            @Override
            public void focusLost(FocusEvent e) {
                validaCEP();
            }
        }));
        
        frm.getTxtUF().addActionListener(comboCidade());
    }
    
    @Override
    protected ActionListener procurar(){
        return e->{
            frmConsulta=new ConsultaBasicaView(null, true);
            DefaultTableModel modeloConsulta = (DefaultTableModel) frmConsulta.getTabelaConsulta().getModel();
            modeloConsulta.addColumn("Codigo");
            modeloConsulta.addColumn("Cliente");
            frmConsulta.getBtnConsulta().addActionListener(evt->{
                modeloConsulta.setRowCount(0);
                ArrayList<Cliente> arr=Cliente.buscaNome(frmConsulta.getTxtProcura().getText());
                for(Cliente item:arr){                       
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
            Cliente cliente=(Cliente)Sessao.getInstance().getResultadoConsulta();
            
            if(cliente!=null){
                clienteAtivo=cliente;
                frm.visualizar();
            }else
                frm.cancelar(); 
            preencheTela();
        };
   }

    private void preencheTela() {
        habilitaEdicao(false);
        if(clienteAtivo==null){
            limpar();
        }else{
            frm.getTxtBairro().setText(clienteAtivo.getBairro());
            frm.getTxtCEP().setText(Mascara.formata(Mascara.CEP, clienteAtivo.getCep()));
            if(clienteAtivo.getTelefone().length()>0)
                frm.getTxtTelefone().setText(Mascara.formata(
                        clienteAtivo.getTelefone().length()==11?
                           Mascara.TELEFONE_9:Mascara.TELEFONE
                        , clienteAtivo.getTelefone()));
            frm.getTxtNumero().setText(clienteAtivo.getNumero());
            frm.getTxtCodigo().setText(clienteAtivo.getCodigo()+"");
            frm.getTxtDocumento().setText(Mascara.formata(clienteAtivo.getDocumento().length()==11?
                   Mascara.CPF:Mascara.CPF,
                   clienteAtivo.getDocumento()));
            frm.getTxtEmail().setText(clienteAtivo.getEmail());
            frm.getTxtEndereco().setText(clienteAtivo.getEndereco());
            frm.getTxtNome().setText(clienteAtivo.getNome());
            
            setValorComboUF(frm.getTxtUF(),clienteAtivo.getCidade().getUF());
            carregaCidadesPorUF(frm.getTxtUF(), frm.getTxtCidade());
            setValorComboCidade(frm.getTxtCidade(), clienteAtivo.getCidade().getNome());
        }
    }

    @Override
    protected ActionListener cancelar() {
      return (e)->{
            frm.cancelar();
            preencheTela();
       };
    }

    private void limpar() {
        frm.getTxtBairro().setText("");
        frm.getTxtCEP().setText("");
        frm.getTxtTelefone().setText("");
        frm.getTxtNumero().setText("");
        frm.getTxtCodigo().setText("");
        frm.getTxtDocumento().setText("");
        frm.getTxtEmail().setText("");
        frm.getTxtEndereco().setText("");
        frm.getTxtNome().setText("");
        
        frm.getTxtUF().setSelectedIndex(-1);
        frm.getTxtCidade().removeAllItems();
        
        frm.getTxtBairro().setBackground(java.awt.Color.WHITE);
        frm.getTxtCEP().setBackground(java.awt.Color.WHITE);
        frm.getTxtNumero().setBackground(java.awt.Color.WHITE);
        frm.getTxtDocumento().setBackground(java.awt.Color.WHITE);
        frm.getTxtEndereco().setBackground(java.awt.Color.WHITE);
        frm.getTxtNome().setBackground(java.awt.Color.WHITE);

        frm.getTxtBairro().setEnabled(false);
        frm.getTxtCEP().setEnabled(false);
        frm.getTxtTelefone().setEnabled(false);
        frm.getTxtNumero().setEnabled(false);
        frm.getTxtCodigo().setEnabled(false);
        frm.getTxtDocumento().setEnabled(false);
        frm.getTxtEmail().setEnabled(false);
        frm.getTxtEndereco().setEnabled(false);
        frm.getTxtNome().setEnabled(false);
        frm.getTxtUF().setEnabled(false);
        frm.getTxtCidade().setEnabled(false);
        frm.getTxtTipoPessoa().setEnabled(false);
        frm.getTxtSexo().setEnabled(false);
        
    }

    @Override
    protected ActionListener gravar() {
     return  e->{
            int cod=frm.getTxtCodigo().getText().equals("")?0:Integer.parseInt(frm.getTxtCodigo().getText());
            Cliente c=new Cliente(cod, frm.getTxtNome().getText(), frm.getTxtDocumento().getText(), 
                    frm.getTxtEndereco().getText(),frm.getTxtNumero().getText(), frm.getTxtBairro().getText(), 
                    frm.getTxtCEP().getText(),(String)frm.getTxtSexo().getSelectedItem(), frm.getTxtEmail().getText(), 
                    frm.getTxtTelefone().getText(), 
                    frm.getTxtTipoPessoa().getSelectedItem().toString().equals("Física")?"F":"J", 
                    (Cidade)frm.getTxtCidade().getSelectedItem());
            Erro erro=c.validar();
            if(!erro.isEmpty())
               Mensagem.atencao(erro.getErroEmLinha());
            else if(c.salvar()){
                Mensagem.sucesso();
                clienteAtivo=c;
                frm.getBtnCancelar().doClick();
            }
        };
    }

    @Override
    protected ActionListener novo() {
        return e->{
            if(VerificaPermissao.gravarPermitido(frm)){
                frm.incluir();
                habilitaEdicao(true);
            }else
                Mensagem.permissaoGravar();
        };
    }

    private void habilitaEdicao(boolean editar) {
            frm.getTxtBairro().setEnabled(editar);
            frm.getTxtCEP().setEnabled(editar);
            frm.getTxtTelefone().setEnabled(editar);
            frm.getTxtNumero().setEnabled(editar);
            frm.getTxtCodigo().setEnabled(editar);
            frm.getTxtDocumento().setEnabled(editar);
            frm.getTxtEmail().setEnabled(editar);
            frm.getTxtEndereco().setEnabled(editar);
            frm.getTxtNome().setEnabled(editar);
            frm.getTxtUF().setEnabled(editar);
            frm.getTxtCidade().setEnabled(editar);
            frm.getTxtTipoPessoa().setEnabled(editar);
            frm.getTxtSexo().setEnabled(editar);
    }


    private ActionListener comboCidade() {
        return e->{
            if(frm.getTxtUF().getSelectedIndex()>-1){
                carregaCidadesPorUF(frm.getTxtUF(),frm.getTxtCidade());
            }
        };
    }

    @Override
    protected ActionListener alterar() {
      return e->{
            if(VerificaPermissao.gravarPermitido(frm)){    
                frm.alterar();
                habilitaEdicao(true);
            }else
                Mensagem.permissaoGravar();
        };
    }

    @Override
    protected ActionListener excluir() {
      return e->{
            if(VerificaPermissao.excluirPermitido(frm)){
                if(Mensagem.confirmaExclusao()){
                    clienteAtivo.setExclusao(new Date());
                    if(clienteAtivo.excluir()){
                        Mensagem.sucesso();
                        clienteAtivo=null;
                    }else
                        Mensagem.atencao("Erro ao excluir!");
                    
                }
            }else{
                Mensagem.permissaoExcluir();
            }
            preencheTela();
            cancelar();
        };
    }
    public static void inicializar() {
        frm=new CadastroClienteView(null, true);
        new CadastroClienteController(frm);
        frm=null;
        System.gc();
    }
    
}