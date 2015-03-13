package com.kpi.education.services;

import com.kpi.education.businesslogic.Shop;
import com.kpi.education.businesslogic.user.ManagerUser;
import com.kpi.education.dao.ManagerUserDAO;
import com.kpi.education.dao.ShopDAO;
import com.kpi.education.exceptions.DuplicatedKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopService {

    private ShopDAO shopDAO;
    private ManagerUserDAO managerUserDAO;

    @Autowired
    public void setShopDAO(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }
    @Autowired
    public void setManagerUserDAO(ManagerUserDAO managerUserDAO) {
        this.managerUserDAO = managerUserDAO;
    }

    @Transactional(noRollbackFor = DuplicatedKeyException.class)
    public Shop create(Shop object, int managerUserID) {
        ManagerUser managerUser = managerUserDAO.get(managerUserID);
        object.setManager(managerUser);
        Shop shop = shopDAO.create(object);
        return shop;
    }

    @Transactional(readOnly = true)
    public Shop get(Integer key) {
        Shop shop = shopDAO.get(key);
        return shop;
    }


    public Shop update(Shop object) {
        Shop shop = shopDAO.update(object);
        return shop;
    }

    public boolean delete(Shop object) {
        boolean res = shopDAO.delete(object);
        return res;
    }
}
