package app.mytweetapp.models;

import java.util.List;


public class User {

    public String _id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public List<String> following;
    public List<String> followers;


    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
