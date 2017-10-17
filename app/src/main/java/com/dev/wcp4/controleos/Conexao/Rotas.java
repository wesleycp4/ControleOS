package com.dev.wcp4.controleos.Conexao;

public class Rotas {
    private static final String ROOT_URL = "http://192.168.1.102/ControleOS/SERVICOS/";

    public static final String URL_LOGIN = ROOT_URL+"usuarioLogin.php";
    public static final String URL_LOGINADM = ROOT_URL+"usuarioLoginAdm.php";

    public static final String CADASTRAR_USUARIO = ROOT_URL+"usuarioNovo.php";
    public static final String CADASTRAR_CLIENTE = ROOT_URL+"clienteNovo.php";
    public static final String CADASTRAR_OS = ROOT_URL+"osNovo.php";
    public static final String CADASTRAR_ACOMPANHAMENTO = ROOT_URL+"acompanhamentoNovo.php";

    public static final String URL_DADOS_OS = ROOT_URL+"osDados.php";
    public static final String URL_DADOS_OS_TUDO = ROOT_URL+"os.php";
    public static final String URL_DADOS_CLIENTE = ROOT_URL+"clienteDados.php";
    public static final String URL_DADOS_CLIENTE_TUDO = ROOT_URL+"cliente.php";
    public static final String URL_DADOS_ACOMPANHAMENTO = ROOT_URL+"acompanhamentoDados.php";

    public static final String URL_UPDATE_CLIENTE = ROOT_URL+"clienteUpdate.php";

}
