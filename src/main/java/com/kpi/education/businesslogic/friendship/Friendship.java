package com.kpi.education.businesslogic.friendship;

import com.kpi.education.businesslogic.data.State;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "friendship")
@NamedQueries(value = {
        @NamedQuery(name = "user.list", query = "select f.couple.applicant from Friendship f where f.couple.acceptor = :user and f.state = :state"),
        @NamedQuery(name = "user.reverse.list", query = "select f.couple.acceptor from Friendship f where f.couple.applicant = :user and f.state = :state")
})
public class Friendship {

    @EmbeddedId
    private Couple couple;

    @Enumerated(EnumType.STRING)
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Couple getCouple() {
        return couple;
    }

    public void setCouple(Couple couple) {
        this.couple = couple;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "couple=" + couple +
                ", state=" + state +
                '}';
    }
}
