package Entity;

import Entity.Admin;

public class Admin {
    private String user;
    private String password;

    public String getUser() {
        return this.user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Admin() {
    }


    public Admin(String user, String password) {
        this.user = user;
        this.password = password;
    }
}