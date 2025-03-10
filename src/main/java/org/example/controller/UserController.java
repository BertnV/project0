package org.example.controller;

import io.javalin.http.Context;
import org.example.dao.UserDao;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

    public class UserController {

        //----------dependency--------------
        UserService userService;


        //--------creates  constructor of our user service dependency ---------------
        public UserController() {
            // ------- creates new instance of user service------------
            this.userService = new UserService();
        }

        //-----------register method---------------
        // parameter that represents the HTTP request and response context
        public void register(Context ctx) {
            //   This method extracts the request body and converts it into a User object
            User user = ctx.bodyAsClass(User.class);


            User registeredUser = userService.registerUser(user);
            //returns the registered user data
            ctx.json(registeredUser);

        }
        /*
         * GET /users
         * Returns JSON of all users
         */
        public void getAllUsers (Context ctx){
            List<User> users = userService.getAllUsers();
            ctx.json(users);


        }
    }