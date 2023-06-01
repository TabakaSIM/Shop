package pl.tabaka.sklep.artykuly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tabaka.sklep.GUI.IGUI;
import pl.tabaka.sklep.models.users.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class ListaProduktow implements IListaProduktow{
    @Autowired
    private IGUI gui;
    private final Map<String,Produkt> listaProduktow = new HashMap<>();
    private static final ListaProduktow instance = new ListaProduktow();

    public void register(Produkt produkt) {
        listaProduktow.put(produkt.getNazwa(),produkt);
    }

    private Produkt findProduct(String nazwa){
        return listaProduktow.get(nazwa);
    }

    public void show(boolean isAdmin) {
        listaProduktow.forEach((key, produkt) ->{
            if(produkt.getIloscNaStanie()>0 | isAdmin) {
                gui.showProdukt(produkt);
            }
        });
    }

    public void kup(User user) {
        String produkt = gui.showKupowanieNazwa();
        if(!listaProduktow.containsKey(produkt)){
            gui.showZakupyNoProductError();
            return;
        }
        int ilosc = gui.showKupowanieIlosc();
        Produkt zakupProduktu = findProduct(produkt);

        if(zakupProduktu.getIloscNaStanie()>=ilosc){
                user.obciazRachunek(ilosc , zakupProduktu);
                return;
        } else {
                gui.showZakupyAmountError();
                return;
        }
    }

    public void restock() {
        String nazwa = gui.showRestock();
        if(!listaProduktow.containsKey(nazwa)){
            gui.showZakupyNoProductError();
            return;
        }
        int ilosc = gui.showRestockIlosc();
        if(ilosc<1){
            gui.showRestockErrorAmount();
            return;
        }
        listaProduktow.get(nazwa).restock(ilosc);
        gui.showRestockSucces();
    }

    public Collection<Produkt> getProducts(){
        return listaProduktow.values();
    }

    public static ListaProduktow getInstance(){
        return instance;
    }
}
