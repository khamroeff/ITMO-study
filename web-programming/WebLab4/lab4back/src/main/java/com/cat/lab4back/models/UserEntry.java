package com.cat.lab4back.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class UserEntry implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String login;
    private String passHash;
    private String passSalt;

    public UserEntry(String login, String passHash, String passSalt) {
        this.login = login;
        this.passHash = passHash;
        this.passSalt = passSalt;
    }

    public UserEntry() {
        this("", "", "");
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassHash() {
        return passHash;
    }
    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getPassSalt() {
        return passSalt;
    }
    public void setPassSalt(String passSalt) {
        this.passSalt = passSalt;
    }
}
