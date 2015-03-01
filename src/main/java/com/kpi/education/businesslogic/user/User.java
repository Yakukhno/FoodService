package com.kpi.education.businesslogic.user;

import com.kpi.education.businesslogic.Message;
import com.kpi.education.businesslogic.Photo;
import com.kpi.education.businesslogic.data.Gender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@NamedQueries(value = {
        @NamedQuery(name = "list.byKeyword.name", query = "from User u where u.firstName like :keyword or u.lastName like :keyword"),
        @NamedQuery(name = "list.byKeyword.personalData", query = "from User u where u.personalData like :keyword "),
        @NamedQuery(name = "list.byKeyword.login", query = "from User u where u.login like :keyword"),
        @NamedQuery(name = "list.byGender", query = "from User u where u.gender = gender"),
})
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String personalData;
    
    @OneToOne
    private Photo photo;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> sentMessages = new ArrayList<Message>();

    @ManyToMany(mappedBy = "receivers", cascade = CascadeType.ALL)
    private List<Message> receivedMessages = new ArrayList<Message>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPersonalData() {
        return personalData;
    }

    public void setPersonalData(String personalData) {
        this.personalData = personalData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", personalData='" + personalData + '\'' +
                ", gender=" + gender +
                ", sentMessages=" + sentMessages +
                ", receivedMessages=" + receivedMessages +
                '}';
    }
}