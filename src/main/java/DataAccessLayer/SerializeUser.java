package DataAccessLayer;

import BusinessLayer.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SerializeUser {
    private File FileUsers;

    public SerializeUser()
    {
        FileUsers = new File("UserLog.ser");
        if (!FileUsers.exists()) {
            try
            {
                FileOutputStream file = new FileOutputStream(FileUsers);
                file.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addUser(User user)
    {
        List<User> list;
        try
        {
            list = getUsers();
            if (list.size() == 0) {
                list = new ArrayList<>();
            }
            FileOutputStream file = new FileOutputStream(FileUsers);
            ObjectOutputStream out = new ObjectOutputStream(file);
            list.add(user);
            out.writeObject(list);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<User> getUsers()
    {
        List<User> RegisteredUsers = new ArrayList<>();
        try
        {
            FileInputStream file = new FileInputStream(FileUsers);
            ObjectInputStream in = new ObjectInputStream(file);
            RegisteredUsers = (List<User>) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("OK");
            e.printStackTrace();
        }
        return RegisteredUsers;
    }
}