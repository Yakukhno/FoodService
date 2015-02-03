package com.kpi.education.businesslogic.friendship;

import com.kpi.education.businesslogic.data.State;

import javax.persistence.*;

@Entity(name = "com.kpi.education.businesslogic.friendship")
@javax.persistence.Table(name = "friendship")
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
