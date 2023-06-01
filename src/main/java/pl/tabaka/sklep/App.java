package pl.tabaka.sklep;

/*
Obiektowość:
Napisz aplikację SKLEP. Na starcie aplikacja wyświetla menu w którym do wyboru jest:
1. Zaloguj
2. Zarejestruj
3. Exit
Jeśli użytkownik wybierze 3 program się kończy.
Jeśli użytkownik wybierze 2 program pozwala się zarejestrować (wrzucamy do bazy nowegu użytkownika, program sprawdza czy podany login nie jest już zajęty)
Jeśli użytkownik wybierze 1 program pozwala się zalogować.

Aplikacja używa hashowania do obsługi haseł.

Menu po zalogowaniu:
1. Wyświetl listę produktów
2. Kup produkt
3. Wyloguj

Użytkownik może wyświetlić listę produktów (tak jak samochody na zajęciach - zakodowane na sztywno w programie).
Może kupić wybrany produkt - podaje kod produktu albo nazwę, jak wam wygodniej, podaje ilość sztuk którą chce kupić i
aplikacja sprzedaje produkt i przelicza cenę (cena produktu * ilość sztuk).
Aplikacja ma pamiętać również ile produktów jest dostępnych w sklepie.
Użytkownikowi nie mają na liście wyświetlać się produkty których ilość to 0.
Nie powinno się dać również kupić więcej sztuk jakiegoś produktu niż jest w sklepie np. jeśli jest 10 komputerów to nie można kupić 12.
Użytkownicy posiadają pole rola. W programie są dwie role - USER i ADMIN.

Jeśli do programu zaloguje się user z rolą ADMIN w menu jest dodatkowa opcja "4. Uzupełnij produkt". Podając kod produktu czy nazwę oraz ilość zwiększa stan produktu na sklepie. Np. komputerów jest 3, admin dodaje 5 i już jest 8 sztuk możliwych do kupienia.
Program powinien również nie pozwalać użytkownikowi zalogowanemu z rolą USER wybrać 4 opcji w menu.
Tematyka sklepu dowolna

Oczywiście jeśli w programie zalogowany jest admin i listuje sobie produkty to listują mu się również te które mają ilość równą 0.

Użytkownik który się rejestruje zawsze dostaje rolę USER. Admina wrzućcie sobie w kodzie :slightly_smiling_face:

Dla chętnych:
Admin posiada jeszcze jedną dodatkową opcję:
5. Zmień rolę
po wybraniu tej opcji listowane są loginy wszystkich użytkowników wraz z ich rolami. Admin podaje login i nową rolę tego użytkownika (można zmienić rolę USER na ADMIN lub ADMIN na USER dla wybranego użytkownika :slightly_smiling_face:
*/

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.tabaka.sklep.configuration.AppConfiguration;
import pl.tabaka.sklep.core.ICore;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        ICore core = context.getBean(ICore.class);
        core.start();
    }
}
