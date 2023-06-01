package pl.tabaka.sklep.models.users;

import java.util.Collection;

public interface IUserList {
    User findUser(String login);
    void register(User user, boolean fromDB);
    void showUsers();
    boolean hasUser(String name);
    Collection<User> getUsers();
}
