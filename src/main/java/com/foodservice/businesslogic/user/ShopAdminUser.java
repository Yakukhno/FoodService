package com.foodservice.businesslogic.user;

import com.foodservice.businesslogic.Shop;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "shop_admin_user")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class ShopAdminUser extends User {

    private String contactTelephone;

    @JsonManagedReference
    @OneToMany(mappedBy = "shopAdminUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
