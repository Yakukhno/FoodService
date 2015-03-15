package com.kpi.education.businesslogic;

import com.kpi.education.businesslogic.data.State;
import com.kpi.education.businesslogic.user.SimpleUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@javax.persistence.Table(name = "ordering")
public class Ordering {

    @Id
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "service_date")
    private Calendar serviceDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "simple_user_id")
    private SimpleUser client;

    @OneToMany(mappedBy = "ordering", cascade = CascadeType.ALL)
    private List<Position> positions = new ArrayList<Position>();

    @Enumerated(EnumType.STRING)
    private State state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public Calendar getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Calendar serviceDate) {
        this.serviceDate = serviceDate;
    }

    public SimpleUser getClient() {
        return client;
    }

    public void setClient(SimpleUser client) {
        this.client = client;
    }
}
