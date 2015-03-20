package com.foodservice.services;

import com.foodservice.businesslogic.data.State;
import com.foodservice.businesslogic.user.ManagerUser;
import com.foodservice.businesslogic.user.ShopAdminUser;
import com.foodservice.dao.ManagerUserDAO;
import com.foodservice.dao.ShopAdminUserDAO;
import com.foodservice.exceptions.DuplicatedKeyException;
import com.foodservice.exceptions.NoSuchUserException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.MediaName;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Service
@Transactional
public class ManagerUserService implements UserService<ManagerUser> {

    private ManagerUserDAO managerUserDAO;
    private ShopAdminUserDAO shopAdminUserDAO;

    @Autowired
    public void setManagerUserDAO(ManagerUserDAO managerUserDAO) {
        this.managerUserDAO = managerUserDAO;
    }
    @Autowired
    public void setShopAdminUserDAO(ShopAdminUserDAO shopAdminUserDAO) {
        this.shopAdminUserDAO = shopAdminUserDAO;
    }

    @Override
    public ManagerUser create(ManagerUser object) {
        ManagerUser managerUser = managerUserDAO.create(object);
        return managerUser;
    }

    /**
     * Creates new ManagerUser instance by injecting
     * ShopAdminUser with given email.
     * @param object
     * @param shopAdminUserEmail email of ShopAdminUser related to this Manager
     * @return persistence instance of ManagerUser
     */
    public ManagerUser create(ManagerUser object, String shopAdminUserEmail) {
        ShopAdminUser shopAdminUser = shopAdminUserDAO.getByEmail(shopAdminUserEmail);
        if (shopAdminUser == null)
            throw new NoSuchUserException(
                    "There is no ShopAdminUser with email '" + shopAdminUserEmail +"' in the system");
        object.setShopAdminUser(shopAdminUser);
        return create(object);
    }

    @Override
    @Transactional(readOnly = true)
    public ManagerUser get(Integer key) {
        ManagerUser managerUser = managerUserDAO.get(key);
        return managerUser;
    }

    @Override
    @Transactional(readOnly = true)
    public ManagerUser getByEmail(String email) {
        ManagerUser managerUser = managerUserDAO.getByEmail(email);
        return managerUser;
    }

    @Override
    public ManagerUser update(ManagerUser object) {
        ManagerUser managerUser = managerUserDAO.update(object);
        return managerUser;
    }

    public ManagerUser updateState(Integer id, State state) {
        ManagerUser managerUser = managerUserDAO.get(id);
        managerUser.setState(state);
        return managerUserDAO.update(managerUser);
    }

    @Override
    public boolean delete(ManagerUser object) {
        return managerUserDAO.delete(object);
    }
}
