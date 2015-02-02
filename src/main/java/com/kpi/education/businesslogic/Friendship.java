package com.kpi.education.businesslogic;

import com.kpi.education.businesslogic.data.State;
import com.kpi.education.businesslogic.user.User;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "friendship",
        uniqueConstraints = @UniqueConstraint(columnNames = {"applicant_id", "acceptor_id"}))
@NamedQueries(value = {
        @NamedQuery(name = "getUsers1", query = "select f.applicant from Friendship f where f.acceptor = :user and f.state = :state"),
        @NamedQuery(name = "getUsers2", query = "select f.acceptor from Friendship f where f.applicant = :user and f.state = :state"),
        @NamedQuery(name = "changeState", query = "update Friendship f " +
                "set f.state = :state where f.acceptor = :acceptor and f.applicant = :applicant")
})
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @ManyToOne
    @JoinColumn(name = "acceptor_id")
    private User acceptor;

    @Enumerated(EnumType.STRING)
    private State state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(User acceptor) {
        this.acceptor = acceptor;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
