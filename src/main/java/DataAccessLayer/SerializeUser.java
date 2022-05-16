package DataAccessLayer;

import BusinessLayer.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeUser {
    private final File FileUsers;

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

    public void addUser(User user)
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
    }

    public List<User> getUsers()
    {
        List<User> RegisteredUsers = new ArrayList<>();
        try
        {
            FileInputStream file = new FileInputStream(FileUsers);
            ObjectInputStream in = new ObjectInputStream(file);
            RegisteredUsers = (List) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return RegisteredUsers;
    }

    public void updateUser(User currentUser)
    {
        try
        {
            List<User> userList = getUsers();
            int i = 0;
            for (User user : userList) {
                if (user.equals(currentUser)) {
                    i = userList.indexOf(user);
                }
            }
            userList.set(i, currentUser);
            FileOutputStream file = new FileOutputStream(FileUsers);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(userList);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}