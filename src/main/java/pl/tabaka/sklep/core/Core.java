package pl.tabaka.sklep.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tabaka.sklep.GUI.IGUI;
import pl.tabaka.sklep.artykuly.IListaProduktow;
import pl.tabaka.sklep.models.users.IUserList;
import pl.tabaka.sklep.models.users.User;

import java.io.IOException;
import java.util.Scanner;

@Component
public class Core implements ICore {
    @Autowired
    private IGUI gui;
    @Autowired
    private IListaProduktow listaProduktow;
    @Autowired
    private IUserList userList;
    @Autowired
    private ILoader loader;

    @Override
    public void start() {

        try {
            loader.readDataFromFile();
        } catch (IOException e) {
            System.out.println("Database is malformed !!");
            return;
        }

        gui.showWelcomeMessage();
        while(true) {
            switch (gui.showLogginMenu()){
                case "1":
                    gui.showLogginMenuLogowanie();
                    zaloguj();
                    continue;
                case "2":
                    gui.showLogginMenuRejestracja();
                    userList.register(new User(gui.showPodajLoggin(),gui.showPodajHaslo(), 0.0f,false),false);
                    continue;
                case "3":
                    gui.showExitMessage();
                    try {
                        loader.saveDataToFile();
                        return;
                    } catch (IOException e) {
                        System.out.println("Database Error !!");
                    }
                default:
                    gui.showMenuError();
                    continue;
            }
        }
    }

    private void zaloguj(){
        int counter=0;
        while (counter<3){
            counter++;
            User user = userList.findUser(gui.showPodajLoggin());
            String password = gui.showPodajHaslo();
            if(user!=null){
                if(user.matchPassword(password)) {
                    gui.showLogginSucces();
                    this.matchUserMenu(user);
                    return;
                }
            }
            gui.showLogginError();
        }
        gui.showWyjscieDoMenu();
        return;
    }

    private void matchUserMenu(User user){
        if(user.isAdmin()){
            this.adminMenu(user);
        } else {
            this.userMenu(user);
        }
    }

    private void userMenu(User user){
        while(true){
            gui.showUserMenu(user);
            switch (new Scanner(System.in).next()){
                case "1":
                    listaProduktow.show(false);
                    continue;
                case "2":
                    listaProduktow.kup(user);
                    continue;
                case "3":
                    user.doladuj();
                    continue;
                case "4":
                    gui.showUserMenuLogout();
                    return;
                default:
                    gui.showMenuError();
                    continue;
            }
        }
    }

    private void adminMenu(User user){
        while(true){
            gui.showUserMenuAdmin(user);
            switch (new Scanner(System.in).next()){
                case "1":
                    listaProduktow.show(true);
                    continue;
                case "2":
                    listaProduktow.kup(user);
                    continue;
                case "3":
                    user.doladuj();
                    continue;
                case "4":
                    gui.showUserMenuLogout();
                    return;
                case "5":
                    listaProduktow.restock();
                    continue;
                case "6":
                    this.userList.showUsers();
                    continue;
                case "7":
                    zmienRole();
                    continue;
                default:
                    gui.showMenuError();
                    continue;
            }
        }
    }

    private void zmienRole(){
        String name = gui.showZmianaRoli();
        if(!userList.hasUser(name)){
            gui.showZmianaRoliError();
            return;
        }
        this.userList.findUser(name).zmienRole();
        gui.showZmianaRoliSucces();
    }
}
