package com.kpi.education.businesslogic.friendship;

import com.kpi.education.businesslogic.data.State;
import com.kpi.education.businesslogic.user.SimpleUser;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "friendship", uniqueConstraints=
@UniqueConstraint(columnNames = {"applicant_id", "acceptor_id"}))
@NamedQueries(value = {
        @NamedQuery(name = "user.list", query = "select f.applicant from Friendship f where f.acceptor = :user and f.state = :state"),
        @NamedQuery(name = "user.reverse.list", query = "select f.acceptor from Friendship f where f.applicant = :user and f.state = :state")
})
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
