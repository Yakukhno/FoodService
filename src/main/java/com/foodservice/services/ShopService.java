package com.foodservice.services;

import com.foodservice.businesslogic.user.ShopAdminUser;
import com.foodservice.dao.ShopAdminUserDAO;
import com.foodservice.exceptions.DuplicatedKeyException;
import com.foodservice.businesslogic.Shop;
import com.foodservice.dao.ShopDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopService {

    private ShopDAO shopDAO;
    private ShopAdminUserDAO shopAdminUserDAO;

    @Autowired
    public void setShopDAO(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }
    @Autowired
    public void setShopAdminUserDAO(ShopAdminUserDAO shopAdminUserDAO) {
        this.shopAdminUserDAO = shopAdminUserDAO;
    }

    @Transactional(noRollbackFor = DuplicatedKeyException.class)
    public Shop create(Shop object, int managerUserID) {
        ShopAdminUser managerUser = shopAdminUserDAO.get(managerUserID);
        object.setShopAdminUser(managerUser);
        Shop shop = shopDAO.create(object);
        return shop;
    }

    @Transactional(readOnly = true)
    public Shop get(Integer key) {
        Shop shop = shopDAO.get(key);
        return shop;
    }

    @Transactional(readOnly = true)
    public List<Shop> getByShopAdminID(Integer shopAdminID) {
        List<Shop> result = shopDAO.getByManagerID(shopAdminID);
        return result;
    }
    
    @Transactional(readOnly = true)
    public List<Shop> getByCriterion(Map<String, Object> criterionParameters) {
        List<Shop> result = shopDAO.getByCriterion(criterionParameters);
        return result;
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
