import java.io.IOException;

public class Menus {

    public static String menuInicial() {
        return ("**********************\n"
                + "1) Login\n"
                + "2) Registar\n"
                + "0) Sair\n"
                + "**********************");
    }

    public static String paginaInicial() {
        return ("**********************\n"
                + "1) Criar CFD\n"
                + "2) Terminar CFD\n"
                + "3) Consulta de CFDs\n"
                + "4) Consulta de ativos\n"
                + "0) Logout\n"
                + "**********************");
    }

    public static String criarCFD() {
        return ("*********** CRIAR CFD ***********\n"
                + "Escolha ativo:\n"
                + " 1) Ação\n"
                + " 2) Commodities\n"
                + " 3) índices\n"
                + " 4) Moedas\n"
                + "O preço do ativo x é €€€€€\n"
                + "Escolha o tipo de CFD:\n"
                + " 1) Long\n"
                + " 2) Short\n"
                + "Qual o valor a investir?\n"
                + "Pretende definir limites?\n"
                + " 1) Sim\n"
                + " 2) Não\n"
                + "Indique Stop Loss:\n"
                + "Indique Take Profit:\n"
                + "CFD estabelecido\n"
                + "*******************************");
    }

    public static String terminarCFD() {
        return ("*********** Terminar CFD ***********\n"
                + "CFD termindado\n"
                + "Foram adicionados $$$$ ao seu plafond \n"
                + "**********************************");
    }

    public static String consultarCFDS() {
        return ("*********** Consultar CFDs ***********\n"
                + "ID: 123\n"
                + " Nome  : Apple\n"
                + " Tipo: Long\n"
                + " Valor de compra: 3,23\n"
                + " Valor atual: 4,33\n"
                + "ID: 42\n"
                + " Nome: Libra\n"
                + " Tipo: Short\n"
                + " Valor de compra: 1,23\n"
                + " Valor atual: 1,03\n"
                + "*************************************");
    }

    public static String consultarAtivos() {
        return ("*********** Consultar Ativos ***********\n"
                + "ID: 55\n"
                + " Nome  : Apple\n"
                + " Tipo: Ação\n"
                + " Valor de compra: 3,23\n"
                + "ID: 22\n"
                + " Nome: Libra\n"
                + " Tipo: Moeda\n"
                + " Valor de compra: 1,23\n"
                + "*******************************");
    }


    public static void main(String[] args)throws IOException{
        System.out.println(terminarCFD());
    }
}
