package com.foodservice.dao;

import com.foodservice.businesslogic.user.ManagerUser;
import com.foodservice.businesslogic.user.ShopAdminUser;
import com.foodservice.exceptions.DuplicatedKeyException;
import com.foodservice.exceptions.NoSuchUserException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ManagerUserDAO implements UserDAO<ManagerUser, Integer> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public ManagerUser create(ManagerUser object) {
        Session session = sessionFactory.getCurrentSession();
        if (getByEmail(object.getEmail()) != null) throw new DuplicatedKeyException();
        session.persist(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true)
    public ManagerUser get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        ManagerUser managerUser = (ManagerUser) session.get(ManagerUser.class, object);
        return managerUser;
    }

    @Transactional(readOnly = true)
    public List<ManagerUser> getByShopAdminUserID(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        ShopAdminUser shopAdminUser = (ShopAdminUser) session.get(ShopAdminUser.class, id);
        if (shopAdminUser == null)
            throw new NoSuchUserException("There is no persistence instance ShopUserAdmin with ID: " + id);
        List managerUsers = session.createQuery(
                "from ManagerUser where shopAdminUser = :shopAdminUser")
                .setParameter("shopAdminUser", shopAdminUser).list();
        return managerUsers;
    }

    @Override
    @Transactional
    public ManagerUser update(ManagerUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
        return object;
    }

    @Override
    @Transactional
    public boolean delete(ManagerUser object) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete ManagerUser where id = :id");
        query.setParameter("id", object.getId());
        int res = query.executeUpdate();
        return res == 1;
    }

    @Override
    public ManagerUser getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ManagerUser where email = :email");
        query.setParameter("email", email);
        return (ManagerUser) query.uniqueResult();
    }
}
