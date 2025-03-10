package org.example;

import io.javalin.Javalin;
import org.example.controller.LoansController;
import org.example.controller.UserController;


public class Main {
    public static void main(String[] args) {

        //=========================== instantiates controllers ===========================
        UserController userController = new UserController();
        LoansController loansController = new LoansController();

        //------------------------starts javalin --------------------------------------------
        var app = Javalin.create(/*config*/)
                .start(7070);

        //----------- Defines routes using the new {param} syntax-----------------
        app.post("/register", userController::register);
        app.post("/loans", loansController::addLoan);

        app.get("users/{id}/loans/", loansController::getUserLoans);

        app.get("/loans", loansController::getAllLoans);
        app.get("/users", userController::getAllUsers);
        app.put("/loans{id}", loansController::updateLoan);
        app.delete("/loans/{id}", loansController::deleteLoan);
    }
}