package com.kpi.education.businesslogic.user;

import com.kpi.education.businesslogic.Rating;
import com.kpi.education.businesslogic.Reservation;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "SIMPLE_USERS")
public class SimpleUser extends User {

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations = new ArrayList<Reservation>();

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings = new ArrayList<Rating>();

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
