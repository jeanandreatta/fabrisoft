/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrisoft.controller.interfacevalidator;

import fabrisoft.util.Erro;
import fabrisoft.util.ErroException;
import fabrisoft.util.Mascara;
import fabrisoft.util.Mensagem;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javafx.scene.paint.Color;
import javax.swing.JTextField;

/**
 *
 * @author Floripes
 */
public class InterfaceValidator {
    public static void telefone(JTextField telefoneTextField) throws ErroException{
        telefoneTextField.setText(Mascara.removeFormatacao(telefoneTextField.getText()));
            String telefone=telefoneTextField.getText();
            switch (telefone.length()) {
                case 10:
                    telefoneTextField.setText(Mascara.formata("(##) ####-####", telefone));
                    break;
                case 11:
                    telefoneTextField.setText(Mascara.formata("(##) #####-####", telefone));
                    break;
                default:
                    throw new ErroException("Telefone inválido!\nInforme o telefone DDD+FONE sem espaços e sem formatação.\nEx.:1199999999");
            }
    }
    public static void cep(JTextField cepTextField) throws ErroException{
        Erro erro=new Erro();
        cepTextField.setText(Mascara.removeFormatacao(cepTextField.getText()));
        if(cepTextField.getText().length()==8){
            installSucesso(cepTextField);
            cepTextField.setText(Mascara.formata(Mascara.CEP, cepTextField.getText()));
        }else{
            installErro(cepTextField);
            throw new ErroException("CEP Inválido!");
        }
    }
    public static void installErro(JTextField textField){
        textField.setBackground(java.awt.Color.RED);
    }
    
    public static void installSucesso(JTextField textField){
        textField.setBackground(java.awt.Color.GREEN);
    }
    
    public static void addTamanhoTextField(JTextField textField,int minimo,int maximo){
            textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
               
            }
            @Override
            public void focusLost(FocusEvent fe) {
                int tamanho=textField.getText().length();
               if(tamanho>=minimo&&tamanho<=maximo)
                   installSucesso(textField);
               else
                   installErro(textField);
            }
        });
    }
}
