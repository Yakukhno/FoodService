package com.foodservice.businesslogic;

import com.foodservice.businesslogic.data.State;
import com.foodservice.businesslogic.user.SimpleUser;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@javax.persistence.Table(name = "reservation")
public class Reservation {

    @Id
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "table_id")
    private com.foodservice.businesslogic.Table table;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "simple_user_id")
    private SimpleUser client;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startpoint;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endpoint;

    @Enumerated(EnumType.STRING)
    private State state;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public com.foodservice.businesslogic.Table getTable() {
        return table;
    }

    public void setTable(com.foodservice.businesslogic.Table table) {
        this.table = table;
    }

    public SimpleUser getClient() {
        return client;
    }

    public void setClient(SimpleUser client) {
        this.client = client;
    }

    public Calendar getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(Calendar startpoint) {
        this.startpoint = startpoint;
    }

    public Calendar getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Calendar endpoint) {
        this.endpoint = endpoint;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
