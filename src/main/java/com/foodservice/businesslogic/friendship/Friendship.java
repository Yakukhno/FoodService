package com.foodservice.businesslogic.friendship;

import com.foodservice.businesslogic.data.State;
import com.foodservice.businesslogic.user.SimpleUser;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@javax.persistence.Table(name = "friendship", uniqueConstraints=
@UniqueConstraint(columnNames = {"applicant_id", "acceptor_id"}))
public class Friendship {

    @Id
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_id")
    private SimpleUser applicant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "acceptor_id")
    private SimpleUser acceptor;

    @Enumerated(EnumType.STRING)
    private State state;

    public Friendship(SimpleUser applicant, SimpleUser acceptor, State state) {
        this.applicant = applicant;
        this.acceptor = acceptor;
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimpleUser getApplicant() {
        return applicant;
    }

    public void setApplicant(SimpleUser applicant) {
        this.applicant = applicant;
    }

    public SimpleUser getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(SimpleUser acceptor) {
        this.acceptor = acceptor;
    }
}
