package Presentation;

import Business.FacadeNegociador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    private void showMenuInicial() {
        List<String> options = new ArrayList<>(Arrays.asList("Login", "Registar", "Sair"));
        System.out.print(listOptions(options));
        int choice = chooseOption(2, options.size());
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
        int choice = chooseOption(4, options.size());
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
    }

    private void showConsultarCFDs() {
    }

    private void showEncerrarCFD() {
    }

    private void showEstabelecerCFD() {
    }

    private void showMenuRegistar() {
    }

    private void showAdeus() {
        System.out.println("Obrigado por usares a ESS Trading Platform! Esperamos ver-te novamente em breve!");
    }

}
