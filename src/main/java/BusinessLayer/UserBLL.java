package BusinessLayer;

import DataAccessLayer.SerializeUser;

import java.util.ArrayList;
import java.util.List;

public class UserBLL {
    SerializeUser serializer = new SerializeUser();

    public boolean addUser(User newUser)
    {
        if (!findUser(newUser))
        {
            serializer.addUser(newUser);
            return true;
        }
        return false;
    }

    public boolean findUser(User newUser)
    {
        List<User> list = serializer.getUsers();
        for (User u : list) {
            if (u.getUsername().equals(newUser.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public String verifyAndFindType(User user)
    {
        List<User> list = serializer.getUsers();
        for (User u : list) {
            if (u.getPassword().equals(user.getPassword()) && u.getUsername().equals(user.getUsername())) {
                return u.getType();
            }
        }
        return null;
    }

    public String findType(User user)
    {
        String type = "";
        List<User> list = serializer.getUsers();
        for (User u : list) {
            if (u.equals(user))
            {
                type += u.getType();
                break;
            }
        }
        return type;
    }
}
