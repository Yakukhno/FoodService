package com.kpi.education.businesslogic.user;

import com.kpi.education.businesslogic.Shop;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "MANAGER_USERS")
public class ManagerUser extends User {

    @OneToMany(mappedBy = "manager")
    private List<Shop> shops = new ArrayList<Shop>();


    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
