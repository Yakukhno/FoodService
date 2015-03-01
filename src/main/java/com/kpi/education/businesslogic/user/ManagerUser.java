package com.kpi.education.businesslogic.user;

import com.kpi.education.businesslogic.Shop;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "manager_user")
@Cacheable()
public class ManagerUser extends User {

    private String contactTelephone;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Shop> shops = new ArrayList<Shop>();


    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
