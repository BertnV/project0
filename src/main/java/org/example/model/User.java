package org.example.model;

public class User {
    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    //This is a no-argument constructor, which is required for certain use
    // cases (such as deserialization, or when a framework like Jackson or
    // JAX-RS is automatically converting JSON into a User object).

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

}

/*
Fields to hold the user's data: id, username, and password.
A no-argument constructor for instantiation without setting any properties.
A parameterized constructor to set the username and password.
Getter and setter methods to access and modify the user's fields.
In your application, this class is used as the model object that gets passed around.
For example, the UserController and UserService classes will work with User objects,
as seen in the code you’ve shared earlier, to interact with the database or handle HTTP requests.

 */