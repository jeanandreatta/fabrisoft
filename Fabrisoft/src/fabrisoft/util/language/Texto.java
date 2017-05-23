/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrisoft.util.language;

/**
 *
 * @author Floripes
 */
public class Texto {
    public static final String TITULO="FABRISOFT";
    
//MENSAGENS 
    //public static final String MENSAGEM_ ="";
    public static final String MENSAGEM_SISTEMA_FINALIZADO ="Se você excluir o seu registro o "
            + "sistema será finalizado!\n"
            + "Deseja continuar?";
    public static final String MENSAGEM_ACESSAR_NAO_PERMITIDO="Acesso não permitido!";
    public static final String MENSAGEM_GRAVAR_NAO_PERMITIDO ="Gravar não permitido!";
    public static final String MENSAGEM_EXCLUIR_NAO_PERMITIDO="Excluir não permitido!";
    public static final String FORMULARIO_NAO_DEFINIDO="Sem nenhum formulário definido!";
    public static final String MENSAGEM_SEM_PERMISSAO="Sem Permissão!!!";
    public static final String MENSAGEM_ATENCAO= "Atenção!!!";
    public static final String MENSAGEM_SEM_USUARIO="Nenhum usuário cadastrado!\nDeseja cadastrar agora?";
    public static final String MENSAGEM_USUARIO_CURTO="Usuário muito curto.";
    public static final String MENSAGEM_SENHA_CURTA="Senha muito curta.";
    public static final String MENSAGEM_USUARIO_EXISTE="Usuário já cadastrado.";
    public static final String MENSAGEM_ULTIMO_USUARIO="Você é o último usuário.\n"
            + "Após a exclusão será necessário criar outro usuário!"
            + "\nDeseja continuar?";
    
    //SUCESSO
    //public static final String SUCESSO_ ="";
    public static final String SUCESSO_PRIMEIRO_USUARIO="Usuário cadastrado com sucesso!\nNa próxima tela preencha com o usuário e senha cadastrados!";

    //ERROS
    //public static final String ERRO_ ="";
    public static final String ERRO_FORM_ERRADO="Tipo de form errado!";
    public static final String ERRO_ULTIMO_ADMINISTRADOR="Último administrador!\nAlteração não permitida!";
    public static final String ERRO_ADM_ALTERA_ADM="Somente administrador tem permissão para criar ou alterar administrador";
    public static final String ERRO_CONEXAO="Erro na conexão com o banco tente novamente!";
}
