package com.kpi.education.businesslogic;

import com.kpi.education.businesslogic.user.User;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@javax.persistence.Table(name = "RESERVATIONS")
public class Reservation {

    @Id
    private int id;

    @ManyToOne
    private Table table;

    @ManyToOne
    private User client;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startpoint;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endpoint;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
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
}
