package com.kpi.education.dao;

import com.kpi.education.businesslogic.user.ManagerUser;
import com.kpi.education.exceptions.DuplicatedKeyException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ManagerUserDAO implements CRUD<ManagerUser, Integer> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor =  DuplicatedKeyException.class)
    public ManagerUser create(ManagerUser object) {
        Session session = sessionFactory.getCurrentSession();
        if (getByEmail(object.getEmail()) != null) throw new DuplicatedKeyException();
        session.persist(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public ManagerUser get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        ManagerUser managerUser = (ManagerUser) session.get(ManagerUser.class, object);
        return managerUser;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public ManagerUser getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ManagerUser where email = :email");
        query.setParameter("email", email);
        return (ManagerUser) query.uniqueResult();
    }
    
//    @Transactional(readOnly = true)
//    public ManagerUser getMainAttributes(Integer id) {
//        Session session = sessionFactory.getCurrentSession();
//        Criteria cr = session.createCriteria(ManagerUser.class)
//                .setProjection(Projections.projectionList()
//                        .add(Projections.property("firstName"), "firstName")
//                        .add(Projections.property("lastName"), "lastName")
//                        .add(Projections.property("email"), "email"))
//                .add(Restrictions.eq("id", id))
//                .setResultTransformer(Transformers.aliasToBean(ManagerUser.class));
//        ManagerUser managerUser = (ManagerUser) cr.uniqueResult();
//        return managerUser;
//    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ManagerUser update(ManagerUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
        return object;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(ManagerUser object) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete ManagerUser where id = :id");
        query.setParameter("id", object.getId());
        int res = query.executeUpdate();
        return res == 1 ? true : false;
    }
}
