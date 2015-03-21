package com.foodservice.dao;

import com.foodservice.businesslogic.data.SystemStatus;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional //isolation by default to improve performance
public class UserCommonDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets systemStatus for users with for users with defined identifiers
     * @param identifiers
     * @return List of systemStatus relatively to each identifier
     */
    public SystemStatus[] getSystemStatus(Integer[] identifiers) {
        Session session = sessionFactory.getCurrentSession();
        SystemStatus[] statuses = new SystemStatus[identifiers.length];
        Query query = session.createQuery("SELECT u.systemStatus from User u where id = :id");
        int i = 0;
        for (Integer id : identifiers) {
            query.setParameter("id", id);
            statuses[i++] = (SystemStatus) query.uniqueResult();
        }
        return statuses;
    }

    public boolean changeSystemStatus(Integer id, SystemStatus status) {
        Session session = sessionFactory.getCurrentSession();
        int res = session.createQuery("update User set systemStatus = :status where id = :id")
                .setParameter("status", status).setParameter("id", id).executeUpdate();
        return res == 1;
    }
}
