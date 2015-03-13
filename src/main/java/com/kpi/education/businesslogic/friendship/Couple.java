package com.kpi.education.businesslogic.friendship;

import com.kpi.education.businesslogic.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Couple implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "acceptor_id")
    private User acceptor;

    public Couple(User applicant, User acceptor) {
        this.applicant = applicant;
        this.acceptor = acceptor;
    }

    public Couple() {
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public User getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(User acceptor) {
        this.acceptor = acceptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Couple)) return false;

        Couple couple = (Couple) o;

        if (!acceptor.equals(couple.acceptor)) return false;
        if (!applicant.equals(couple.applicant)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = applicant.hashCode();
        result = 31 * result + acceptor.hashCode();
        return result;
    }
}
