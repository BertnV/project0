package org.example.dao;

import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao{

    String url = "jdbc:postgresql://localhost:5432/project_0";
    String dbUser = "postgres";
    String dbPassword = "password";


    // method to receive the data from the service to pass it to the database
    public User registerUser(User user) {
        //Query to insert data to the database
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        //this a connection to the db
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);

             //this prepares the sql statement for execution returns generated keys
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

             //Sets the first placeholder (?) in the SQL query with the username of the user object.
             stmt.setString(1, user.getUsername());
             stmt.setString(2, user.getPassword());
             //Executes the INSERT SQL statement, which adds the new user into the users table.
             stmt.executeUpdate();

             // Retrieve auto-generated ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {

                //
                if (generatedKeys.next()) {
                    // The first column in 'generatedKeys' is the newly inserted ID
                    int newId = generatedKeys.getInt(1);
                    user.setId(newId);
                }
            }
                //If any SQLException occurs during the database interaction,
                // it will be caught here, and the error will be printed to the console.
        } catch (SQLException e) {
            e.printStackTrace();
        }

                // Return the updated User object (now containing its DB-assigned ID)
        return user;
    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")


                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    }



//SUMMARY
// registerUser(User user) takes a User object, inserts it into the database with the username
// and password fields, retrieves the auto-generated ID (if applicable), and sets this ID on the
// User object.
//It then returns the updated User object, which now includes its database-assigned ID.
//In the context of your application, this UserDao method allows the UserService to persist
// new users to the database and obtain their unique identifiers for further use
// (e.g., linking them to other data, like tasks or profiles).