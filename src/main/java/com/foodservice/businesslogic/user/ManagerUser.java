package com.foodservice.businesslogic.user;

import com.foodservice.businesslogic.data.State;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "manager_user")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class ManagerUser extends User {

    @ManyToOne
    private ShopAdminUser shopAdminUser;

    /**
     * Determines wether the shop administrator
     * accepts this manager as employee
     */
    @Enumerated(EnumType.STRING)
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ShopAdminUser getShopAdminUser() {
        return shopAdminUser;
    }

    public void setShopAdminUser(ShopAdminUser shopAdminUser) {
        this.shopAdminUser = shopAdminUser;
    }
}
