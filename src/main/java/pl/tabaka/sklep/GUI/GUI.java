package pl.tabaka.sklep.GUI;

import org.springframework.stereotype.Component;
import pl.tabaka.sklep.artykuly.Produkt;
import pl.tabaka.sklep.models.users.User;

import java.util.Scanner;

@Component
public class GUI implements IGUI{

    private static final GUI instance = new GUI();

    public GUI() {
    }

    public static GUI getInstance(){
        return instance;
    }

    @Override
    public void showWelcomeMessage() {
        System.out.println("Witamy w sklepie u Thaumaturge'a");
    }

    @Override
    public String showLogginMenu() {
        System.out.println("1. Zaloguj");
        System.out.println("2. Zarejestruj");
        System.out.println("3. Exit");
        return new Scanner(System.in).nextLine();
    }

    @Override
    public void showMenuError() {
        System.out.println("Nie ma takiej komendy! Sprobuj ponownie!");
    }

    @Override
    public void showExitMessage() {
        System.out.println("Dziekujemy za wizyte w sklepie! Zapraszamy ponownie!");
    }

    @Override
    public void showLogginMenuLogowanie() {
        System.out.println("Wybrales zalogowanie sie.");
    }

    @Override
    public void showLogginMenuRejestracja() {
        System.out.println("Wybrales rejestrowanie sie.");
    }

    @Override
    public void showRegisterError() {
        System.out.println("Login zajety!");
    }

    @Override
    public String showPodajLoggin() {
        System.out.println("Podaj login:");
        return new Scanner(System.in).nextLine();
    }

    @Override
    public String showPodajHaslo() {
        System.out.println("Podaj haslo");
        return new Scanner(System.in).nextLine();
    }

    @Override
    public void showLogginError() {
        System.out.println("Login lub haslo niepoprawne!");
    }

    @Override
    public void showLogginSucces() {
        System.out.println("Pomyslnie zalogowano!");
    }

    @Override
    public void showUserMenu(User user) {
        System.out.println("Masz na koncie: " + user.getMoney());
        System.out.println("1. Wyświetl listę produktów");
        System.out.println("2. Kup produkt");
        System.out.println("3. Doladuj konto");
        System.out.println("4. Wyloguj");
    }

    @Override
    public void showUserMenuAdmin(User user) {
        this.showUserMenu(user);
        System.out.println("5. Restock");
        System.out.println("6. Wyswietl uzytkownikow");
        System.out.println("7. Zmien ustawienia uzytkownikow");
    }

    @Override
    public void showUserMenuLogout() {
        System.out.println("Pomyslnie wylogowano!");
    }

    @Override
    public void showRegisterSucces() {
        System.out.println("Pomyślnie zarejestrowano");
    }

    @Override
    public void showProdukt(Produkt produkt) {
        System.out.println(produkt.getNazwa() + " - cena: " + produkt.getCena() + " - ilosc na stanie: " + produkt.getIloscNaStanie());
        System.out.println();
    }

    @Override
    public void showUser(User user) {
        System.out.println("Login: " + user.getLogin() + " - Stan konta: " + user.getMoney());
    }

    @Override
    public String showKupowanieNazwa() {
        System.out.println("Wpisz nazwe produktu który chcesz kupić:");
        return new Scanner(System.in).nextLine();
    }

    @Override
    public int showKupowanieIlosc() {
        System.out.println("Wpisz ilosc produktu który chcesz kupić:");
        return scanGetInt();
    }

    @Override
    public float showUserDoladowanie() {
        System.out.println("Podaj kwote ktora chcesz doladowac konto:");
        return scanGetFloat();
    }

    @Override
    public void showZakupSucces() {
        System.out.println("Zakup pomyślny!");
    }

    @Override
    public void showZakupyMoneyError() {
        System.out.println("Masz za malo pieniedzy na koncie!");
    }

    @Override
    public void showZakupyAmountError() {
        System.out.println("Nie ma takiej ilosci tego produktu na stanie!");
    }

    @Override
    public void showZakupyNoProductError() {
        System.out.println("Nie ma takiego produktu!");
    }

    @Override
    public void showUserPomyslneDoladowanie() {
        System.out.println("Pomyslnie doladowano srodki!");
    }

    @Override
    public void showUserNieudaneDoladowanie(){
        System.out.println("Przegrales!");
    };

    @Override
    public String showRestock() {
        System.out.println("Wpisz nazwe produktu do uzupelnienia:");
        return new Scanner(System.in).nextLine();
    }

    @Override
    public int showRestockIlosc() {
        System.out.println("Wprowadz ile sztuk produktu dodac na polke:");
        return scanGetInt();
    }

    @Override
    public void showRestockSucces() {
        System.out.println("Pomyslnie dodano produkty!");
    }

    @Override
    public void showRestockErrorAmount() {
        System.out.println("Ilosc uzupelniajaca nie moze byc ujemna!");
    }

    @Override
    public String showZmianaRoli() {
        System.out.println("Podaj nazwe uzytkownika ktoremu chcesz zmienic role:");
        return new Scanner(System.in).nextLine();
    }

    @Override
    public void showZmianaRoliError() {
        System.out.println("Nie ma takiego konta!");
    }

    @Override
    public void showZmianaRoliSucces() {
        System.out.println("Pomyslnie zmieniono role!");
    }

    @Override
    public void showWyjscieDoMenu() {
        System.out.println();
        System.out.println("Przekroczyles mozliwa ilosc prob!");
        System.out.println("Wyjscie do menu glownego!");
        System.out.println();
    }

    @Override
    public void showGameError(){
        System.out.println("Kombinacja niepoprawna!");
        System.out.println("Podaj kombinacje jeszcze raz:");
    };

    @Override
    public void showGameCorrect(int length) {
        for (int i=0;i<length;i++) {
            System.out.print("⚫");
        }
        System.out.println();
    }

    @Override
    public void showGameProgress(int black, int white, int length) {
        for(int i=0;i<black;i++){
            System.out.print("⚫");
        }
        for(int i=0;i<white;i++){
            System.out.print("⚪");
        }
        for(int i=0;i<length-black-white;i++){
            System.out.print("❌");
        }
        System.out.println();
    }

    @Override
    public void showGamePoprawnaKombinacja(String combination){
        System.out.println("Poprawna kombinacja to: " + combination);
    }

    private int scanGetInt(){
        Scanner scan;
        do {
            scan  = new Scanner(System.in);
        } while (!scan.hasNextInt());
        return scan.nextInt();
    }

    private float scanGetFloat() {
        Scanner scan;
        do {
            scan  = new Scanner(System.in);
        } while (!scan.hasNextFloat());
        return scan.nextFloat();
    }

}
