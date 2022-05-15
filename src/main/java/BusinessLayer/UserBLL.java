package BusinessLayer;

import DataAccessLayer.SerializeUser;

import java.io.Serializable;
import java.util.List;

public class UserBLL implements Serializable {
    SerializeUser serializer = new SerializeUser();

    public boolean addUser(User newUser)
    {
        if (!findUser(newUser))
        {
            List<User> list = serializer.getUsers();
            if (list.size() == 0) {
                newUser.setId(1);
            }
            else
            {
                int id = list.get(list.size() - 1).getId();
                newUser.setId(id + 1);
            }
            serializer.addUser(newUser);
            return true;
        }
        return false;
    }

    public boolean findUser(User newUser)
    {
        List<User> list = serializer.getUsers();
        for (User u : list) {
            if (u.getPassword().equals(newUser.getPassword()) && u.getUsername().equals(newUser.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String username, String password)
    {
        List<User> list = serializer.getUsers();
        for (User u : list) {
            if (u.getPassword().equals(password) && u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
