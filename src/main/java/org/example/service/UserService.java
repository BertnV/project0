package org.example.service;

import org.example.dao.UserDao;
import org.example.model.Loans;
import org.example.model.User;

import java.util.List;

//--------------------manages business logic related to users---------
public class UserService {

    //----dependency that deals with database operations such as saving and retrieving users
    UserDao userDao;

    //--------- constructor-------------
    public UserService(){
        // initializes the user dao creating a new instance of userDAO
        this.userDao = new UserDao();
    }

    //This method takes a User object as a parameter and passes it
    // to the userDao for registration.
    public User registerUser(User user) {
        return userDao.registerUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}

//In the context of the UserController class (from your previous example),
// this service is used to manage the actual registration logic, acting as a
// bridge between the controller (handling HTTP requests) and the UserDao
// (handling database operations).
