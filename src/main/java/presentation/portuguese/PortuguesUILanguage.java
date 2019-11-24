package presentation.portuguese;

import presentation.UILanguage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PortuguesUILanguage implements UILanguage {

    @Override
    public List<String> getInitialMenuOptions() {
        return new ArrayList<>(Arrays.asList("Login", "Registar", "Sair"));
    }

    @Override
    public String getGoodbyeMessage() {
        return "Obrigado por usares a ESS Trading Platform! Esperamos ver-te novamente em breve!";
    }

    @Override
    public String getInsertYourNif() {
        return "Insira o seu NIF:";
    }

    @Override
    public String getInsertYourPassword() {
        return "Insira a palavra-passe:";
    }

    @Override
    public String getNonExistentCredentials() {
        return "Credenciais não existem no sistema! Vais ser redirecionado para a página de registo!";
    }

    @Override
    public List<String> getInitialPageOptions() {
        return new ArrayList<>(Arrays.asList("Estabelecer CFD", "Encerrar CFD", "Consultar CFDs", "Consultar Ativos", "Adicionar Saldo", "Logout"));
    }

    @Override
    public String getInsertTypeofAsset() {
        return "Que tipo de Ativo deseja consultar?";
    }

    public List<String> getTypesOfAssets() {
        return new ArrayList<>(Arrays.asList("Ações", "Commodities", "Índices", "Moedas"));
    }

    @Override
    public String getAsset() {
        return "Ativo";
    }

    @Override
    public String getUnitsOfAsset() {
        return "Unidades de Ativo";
    }

    @Override
    public String getInvestedValue() {
        return "Valor investido";
    }

    @Override
    public String getInvestedValueInCaseOfRefund() {
        return "Valor em caso de reembolso";
    }

    @Override
    public String getTimestamp() {
        return "Data";
    }

    @Override
    public String getInsertAssetToInvest() {
        return "Indique o ativo no qual deseja investir:";
    }

    @Override
    public String getInsertUnitsToInvest() {
        return "Indique as unidades que deseja adquirir:";
    }

    @Override
    public String getYouHave() {
        return "Possui ";
    }

    @Override
    public String getToInvest() {
        return "€ para investir";
    }

    @Override
    public String getCFDEstablishedWithSuccess() {
        return "CFD estabelecido com sucesso!";
    }

    @Override
    public String getWishToDefine() {
        return "Deseja definir um ";
    }

    @Override
    public String getQuestionYesOrNo() {
        return "?(s/n)";
    }

    @Override
    public String getYouWillInvest() {
        return "Vai investir ";
    }

    @Override
    public String getInsertValue() {
        return "Indique o valor:";
    }

    @Override
    public String getInvalidLimit() {
        return "Limite inválido! Vai estabelecer o CFD sem o limite";
    }
}
