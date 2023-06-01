package pl.tabaka.sklep.artykuly;

import pl.tabaka.sklep.models.users.User;

import java.util.Collection;

public interface IListaProduktow {
    void show(boolean isAdmin);
    void kup(User user);
    void restock();
    void register(Produkt produkt);
    Collection<Produkt> getProducts();
}
