package com.kpi.education.businesslogic;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@javax.persistence.Table(name = "ORDERINGS")
public class Ordering {

    @Id
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar serviceDate;

    private boolean status;

    @OneToMany
    private List<Position> positions = new ArrayList<Position>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Calendar serviceDate) {
        this.serviceDate = serviceDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
