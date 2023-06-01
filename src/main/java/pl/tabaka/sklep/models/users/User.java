package pl.tabaka.sklep.models.users;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.tabaka.sklep.GUI.GUI;
import pl.tabaka.sklep.artykuly.Produkt;
import pl.tabaka.sklep.core.Writable;
import pl.tabaka.sklep.game.Game;

public class User implements Writable {
    private String login;
    private String password;
    private static final String seed = "P9Os1or%4&asd81O%3aTR4Dd0C";

    private float money;
    private boolean isAdmin=false;

    private final GUI gui = GUI.getInstance();

    public User(String login, String password, float money, boolean isAdmin) {
        this.login = login;
        this.password=password;
        this.money = money;
        this.isAdmin=isAdmin;
    }

    public String getLogin() {
        return this.login;
    }

    public boolean matchPassword(String password){
        return this.password.equals(DigestUtils.md5Hex(password+this.seed));
    }

    public void obciazRachunek(int ilosc, Produkt produkt) {
        System.out.println(ilosc* produkt.getCena());
        if(ilosc* produkt.getCena()<=this.money) {
            this.money -= ilosc* produkt.getCena();
            produkt.removeAmount(ilosc);
            gui.showZakupSucces();
            return;
        } else {
            gui.showZakupyMoneyError();
            return;
        }
    }

    public void doladuj() {
        this.dodajSrodki(gui.showUserDoladowanie());
    }

    public void dodajSrodki(float doladowanie){
        if(new Game(gui).play((int) doladowanie)) {
            this.money += doladowanie;
            gui.showUserPomyslneDoladowanie();
            return;
        }
            gui.showUserNieudaneDoladowanie();
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public void zmienRole(){
        this.isAdmin=!isAdmin;
    }

    public float getMoney() {
        return money;
    }

    @Override
    public String toCSV(){
        return new StringBuilder().append(getClass().getSimpleName())
                .append(";")
                .append(this.login)
                .append(";")
                .append(this.password)
                .append(";")
                .append(this.money)
                .append(";")
                .append(this.isAdmin)
                .toString();
    }
}
