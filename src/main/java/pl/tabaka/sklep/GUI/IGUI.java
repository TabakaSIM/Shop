package pl.tabaka.sklep.GUI;

import pl.tabaka.sklep.artykuly.Produkt;
import pl.tabaka.sklep.models.users.User;

public interface IGUI {
    void showWelcomeMessage();

    String showLogginMenu();

    void showMenuError();

    void showExitMessage();

    void showLogginMenuLogowanie();

    void showLogginMenuRejestracja();

    void showRegisterError();

    String showPodajLoggin();

    String showPodajHaslo();

    void showLogginError();

    void showLogginSucces();

    void showUserMenu(User user);

    void showUserMenuAdmin(User user);

    void showUserMenuLogout();

    void showRegisterSucces();

    void showProdukt(Produkt produkt);

    void showUser(User user);

    String showKupowanieNazwa();

    int showKupowanieIlosc();

    float showUserDoladowanie();

    void showZakupSucces();

    void showZakupyMoneyError();

    void showZakupyAmountError();

    void showZakupyNoProductError();

    void showUserPomyslneDoladowanie();

    String showRestock();

    int showRestockIlosc();

    void showRestockSucces();

    void showRestockErrorAmount();

    String showZmianaRoli();

    void showZmianaRoliError();

    void showZmianaRoliSucces();

    void showWyjscieDoMenu();

    void showUserNieudaneDoladowanie();

    void showGameError();

    void showGameCorrect(int length);

    void showGameProgress(int black, int white, int length);

    void showGamePoprawnaKombinacja(String combination);
}
