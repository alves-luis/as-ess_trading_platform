package presentation;

import java.util.List;

public interface UILanguage extends SetCFDUILanguage, GetCFDsUILanguage, GetAssetsUILanguage {
    List<String> getInitialMenuOptions();
    String getGoodbyeMessage();
    // Menu Login
    String getInsertYourNif();
    String getInsertYourPassword();
    String getNonExistentCredentials();
    // Pagina Inicial
    List<String> getInitialPageOptions();
    String getSaldo();
    String getInsertName();
    String getInsertEmail();
    String getInsertSaldo();
    String getNegociatorInserted();
    String getNegociatorNotInserted();
}
