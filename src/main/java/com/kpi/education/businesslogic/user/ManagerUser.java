package com.kpi.education.businesslogic.user;

import com.kpi.education.businesslogic.Shop;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "manager_user")
@NamedQueries(value = {
        @NamedQuery(name = "managerUser.byLogin", query = "from ManagerUser u where u.login = :login"),
        @NamedQuery(name = "managerUser.byLoginAndPassword", query = "from ManagerUser u where u.login = :login and u.password = :password"),
})
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
