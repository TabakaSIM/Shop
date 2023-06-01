package pl.tabaka.sklep.models.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tabaka.sklep.GUI.IGUI;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserList implements IUserList{
    @Autowired
    private IGUI gui;
    private final Map<String,User> userList = new HashMap<>();

    @Override
    public void register(User user, boolean fromDB){
        if(userList.containsKey(user.getLogin())){
            if(!fromDB)gui.showRegisterError();
            return;
        } else {
            userList.put(user.getLogin(), user);
            if(!fromDB)gui.showRegisterSucces();
            return;
        }
    }

    @Override
    public User findUser(String login) {
        return this.userList.get(login);
    }

    @Override
    public void showUsers(){
        for (String key : userList.keySet()){
            gui.showUser(userList.get(key));
        }
    }

    @Override
    public boolean hasUser(String name) {
        return userList.containsKey(name);
    }

    public Collection<User> getUsers(){
        return userList.values();
    }
}
