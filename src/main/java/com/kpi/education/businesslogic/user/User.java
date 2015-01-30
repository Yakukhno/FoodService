package com.kpi.education.businesslogic.user;

import com.kpi.education.businesslogic.Message;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@javax.persistence.Table(name = "USERS")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;
    private String lastName;
    private String login;
    private String password;

    @ManyToMany
    private Set<Message> sentMessages = new HashSet<Message>();

    @ManyToMany
    @JoinTable(name = "FRIENDS")
    private Set<User> friends = new HashSet<User>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(Set<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}