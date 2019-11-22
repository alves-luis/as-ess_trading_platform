package Presentation;

import Business.Ativos.Ativo;
import Business.Ativos.AtivoConsts;
import Business.CFD;
import Business.Exceptions.NegociadorNaoExisteException;
import Business.Exceptions.NegociadorNaoPossuiSaldoSuficienteException;
import Business.FacadeNegociador;

import java.util.*;
import java.util.stream.Collectors;

public class TextUINegociador implements UINegociador {
    private final static String MENU_SEPARATOR = "-";

    private FacadeNegociador facade;
    private Integer nif;

    public TextUINegociador(FacadeNegociador f) {
        this.facade = f;
        this.nif = null;
    }

    public void start() {
        showMenuInicial();
    }

    private static String listOptions(List<String> options) {
        int maxSize = 0;
        StringBuilder opsString = new StringBuilder();
        for(int i = 0; i < options.size(); i++) {
            String option = options.get(i);
            opsString.append(i).append(") ").append(option).append("\n");
            maxSize = Math.max(option.length(), maxSize);
        }
        int n = String.valueOf(options.size()).length();
        String separator = MENU_SEPARATOR.repeat(maxSize + 2 + n) + "\n"; // + 2 for n)_
        return separator + opsString + separator;
    }

    /**
     * @param defaultOption User will pick this option if invalid input
     * @param maxOption Max number in the options
     * @return chosen option
     */
    private static int chooseOption(int defaultOption, int maxOption) {
        Scanner sc = new Scanner(System.in);
        try {
            defaultOption = sc.nextInt();
            if (defaultOption > maxOption)
                defaultOption = maxOption;
        }
        catch (Exception e) {
            System.out.println("Não inseriste um inteiro válido!");
        }
        return defaultOption;
    }

    private static String getString() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    private static int getInt() {
        return chooseOption(0,Integer.MAX_VALUE);
    }

    private static double getDouble() {
        Scanner sc = new Scanner(System.in);
        double result = 0;
        try {
            result = sc.nextDouble();
        }
        catch (Exception e) {
            System.out.println("Não inseriste um valor válido!");
        }
        return result;
    }

    private static boolean getBoolean() {
        Scanner sc = new Scanner(System.in);
        boolean result = false;
        try {
            String s = sc.next();
            if (s.equals("s"))
                result = true;
        }
        catch (Exception e) {
            System.out.println("Não inseriste um valor válido!");
        }
        return result;
    }

    private void showMenuInicial() {
        List<String> options = new ArrayList<>(Arrays.asList("Login", "Registar", "Sair"));
        System.out.print(listOptions(options));
        int choice = chooseOption(2, options.size() - 1);
        switch (choice) {
            case 0: showMenuLogin();
                    break;
            case 1: showMenuRegistar();
                    break;
            default: showAdeus();
                    break;
        }
    }

    private void showMenuLogin() {
        System.out.println("Insira o seu NIF:");
        int nif = getInt();
        System.out.println("Insira a palavra-passe:");
        String pass = getString();
        boolean loggedIn = true; //this.facade.verificarCredenciais(nif, pass);
        if (loggedIn) {
            this.nif = nif;
            showPaginaInicial();
        }
        else {
            System.out.println("Credenciais não existem no sistema! Vais ser redirecionado para a página de registo!");
            showMenuRegistar();
        }
    }

    private void showPaginaInicial() {
        List<String> options = new ArrayList<>(Arrays.asList("Estabelecer CFD", "Encerrar CFD", "Consultar CFDs", "Consultar Ativos", "Logout"));
        System.out.print(listOptions(options));
        int choice = chooseOption(4, options.size() - 1);
        switch (choice) {
            case 0: showEstabelecerCFD();
                    break;
            case 1: showEncerrarCFD();
                    break;
            case 2: showConsultarCFDs();
                    break;
            case 3: showConsultarAtivos();
                    break;
            default: this.nif = null;
                    showMenuInicial();
                    break;
        }
    }

    private void showConsultarAtivos() {
        List<String> options = AtivoConsts.TIPOS_DE_ATIVOS;
        System.out.println("Que tipo de Ativo deseja consultar?");
        System.out.println(listOptions(options));
        int tipo = chooseOption(options.size(), options.size() - 1);
        List<Ativo> ativos = this.facade.getAtivos(options.get(tipo));
        ativos.forEach(a -> System.out.println(a.toString()));
        showPaginaInicial();
    }

    private void showConsultarCFDs() {
        List<CFD> cfds = this.facade.getCFDs(this.nif);
        Map<CFD, Double> valorAtual = new HashMap<>();
        cfds.forEach(c -> valorAtual.put(c,this.facade.getValorAtualCFD(c.getId())));

        for(Map.Entry<CFD, Double> en : valorAtual.entrySet()) {
            CFD cfd = en.getKey();
            List<String> format = new ArrayList<>(Arrays.asList("ID | " + cfd.getId(),
                    "Data | " + cfd.getData().toString(),
                    "Ativo | " + cfd.getIdAtivo(),
                    "Unidades de Ativo | " + cfd.getUnidadesDeAtivo(),
                    "Valor investido | " + cfd.getValorInvestido() + "€",
                    "Valor em caso de reembolso | " + en.getValue() + "€"));
            String delimiter = "-".repeat(format.get(format.size()-1).length());
            System.out.println(delimiter);
            format.forEach(System.out::println);
            System.out.println(delimiter);
        }
        showPaginaInicial();
    }

    private void showEncerrarCFD() {


    }

    private void showEstabelecerCFD() {
        List<String> options = AtivoConsts.TIPOS_DE_ATIVOS;
        System.out.print(listOptions(options));
        int typeOfAtivo = chooseOption(options.size(), options.size() - 1);
        List<Ativo> ativos = this.facade.getAtivos(options.get(typeOfAtivo));

        List<String> ativosAsString = ativos.stream().map(Ativo::toString).collect(Collectors.toList());
        System.out.println("Indique o ativo no qual deseja investir:");
        System.out.print(listOptions(ativosAsString));

        int ativo = chooseOption(ativos.size(), ativos.size() - 1);
        double saldo = this.facade.getSaldo(this.nif);
        System.out.println("Indique as unidades que deseja adquirir:\nPossui " + saldo + "€ para investir");
        double unidades = getDouble();
        double investimento = unidades * ativos.get(ativo).getValorPorUnidade();

        Double stopLossValue = definirLimite("Stop Loss", investimento);
        Double takeProfitValue = definirLimite("Take Profit", investimento);

        try {
            CFD cfd = this.facade.registarCFD(ativos.get(ativo).getId(), this.nif, unidades, stopLossValue, takeProfitValue, "Short");
            System.out.println("CFD estabelecido com sucesso!");
            System.out.println(cfd.toString());
        } catch (NegociadorNaoExisteException e) {
            e.printStackTrace();
        } catch (NegociadorNaoPossuiSaldoSuficienteException e) {
            System.out.println(e.toString());
        }
        showPaginaInicial();
    }

    private Double definirLimite(String nomeLimite, double investimentoBase) {
        System.out.println("Deseja definir um " + nomeLimite + "?(s/n)\nVai investir " + investimentoBase + "€");
        boolean queroLimite = getBoolean();
        Double result = null;
        if (queroLimite) {
            System.out.println("Indique o valor:");
            result = getDouble();
            if (nomeLimite.equals("Take Profit") && result < investimentoBase ||
                    nomeLimite.equals("Stop Loss") && result > investimentoBase)
                System.out.println("Limite inválido! Vai estabelecer o CFD sem o limite");
        }
        return result;
    }

    private void showMenuRegistar() {
    }

    private void showAdeus() {
        System.out.println("Obrigado por usares a ESS Trading Platform! Esperamos ver-te novamente em breve!");
    }

}
