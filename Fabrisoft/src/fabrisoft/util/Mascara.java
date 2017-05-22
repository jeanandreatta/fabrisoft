/*
 * Copyright (C) 2016 Jean.
 */

package fabrisoft.util;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Jean
 */
public class Mascara {
    public static final String TELEFONE="(##) #####-####";
    public static final String TELEFONE_9="(##) ######-####";
    public static final String CNPJ="##.###.###/####-##";
    public static final String CEP="#####-###",CPF="###.###.###-##";
    public static MaskFormatter cnpj() throws ParseException{
        return /*new JFormattedTextField(*/new MaskFormatter(CNPJ);
    }
    public static MaskFormatter cep() throws ParseException{
        return /*new JFormattedTextField(*/new MaskFormatter(CEP);
    }
    public static MaskFormatter cpf() throws ParseException{
        return /*new JFormattedTextField(*/new javax.swing.text.MaskFormatter(CPF);
    }
    public static MaskFormatter telefone() throws ParseException{
        
        return /*new JFormattedTextField(*/new MaskFormatter(TELEFONE);
    }
    
    public static String formata(String mascara, String valor){
		for(int i = 0; i < valor.length(); i++){
			mascara = mascara.replaceFirst("#",valor.substring(i, i + 1));
		}
		return mascara.replaceAll("#", "");
    }
    
    public static String removeFormatacao(String valor){
       valor=valor.replaceAll("[^Z0-9 ]","");
       valor=valor.replace(" ","");
       return valor;
    }
}

