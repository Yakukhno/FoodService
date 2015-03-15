package com.kpi.education.businesslogic;

import com.kpi.education.businesslogic.data.State;
import com.kpi.education.businesslogic.user.SimpleUser;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "rating")
public class Rating {

    @Id
    private int id;

    private int value;
    private String comment;

//    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "simple_user_id")
    private SimpleUser user;

    @Enumerated(EnumType.STRING)
    private State state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
