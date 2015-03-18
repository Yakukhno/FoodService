package com.kpi.education.dao;

import com.kpi.education.businesslogic.Rating;
import com.kpi.education.businesslogic.Shop;
import com.kpi.education.exceptions.DuplicatedKeyException;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Map;

@Repository
public class ShopDAO implements CRUD<Shop, Integer> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Shop create(Shop object) {
        Session session = sessionFactory.getCurrentSession();
        if (get(object.getId()) != null) throw new DuplicatedKeyException();
        session.persist(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true)
    public Shop get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        Shop shop = (Shop) session.get(Shop.class, object);
        return shop;
    }

    @Transactional(readOnly = true)
    public List<Shop> getByManagerID(@PathParam("managerID") Integer managerID) {
        Session session = sessionFactory.getCurrentSession();
        List<Shop> shops = session.createQuery(
                "from Shop where manager.id = :managerID")
                .setParameter("managerID", managerID).list();
        return shops;
    }
    
    @Transactional(readOnly = true)
    public List<Shop> getByCriterion(Map<String, Object> criterionParameters) {
        Session session = sessionFactory.getCurrentSession();

        System.out.println("nameLike" + criterionParameters.get("nameLike"));
        System.out.println("minRating"+ criterionParameters.get("minRating"));
        System.out.println("maxRating"+ criterionParameters.get("maxRating"));
        System.out.println("countryLike"+ criterionParameters.get("countryLike"));
        System.out.println("cityLike"+ criterionParameters.get("cityLike"));
        System.out.println("streetLike"+ criterionParameters.get("streetLike"));
        System.out.println("buildingLike"+ criterionParameters.get("buildingLike"));


        Criteria criteria = session.createCriteria(Shop.class, "s");
        ProjectionList projList = Projections.projectionList();
        projList.add(Projections.property("id"));
        projList.add(Projections.property("name"));
        projList.add(Projections.property("location.country"));
        projList.add(Projections.property("location.city"));
        projList.add(Projections.property("location.street"));
        projList.add(Projections.property("description"));
        criteria.setProjection(projList);
        String param;
        if (!(param = (String) criterionParameters.get("nameLike")).equals("")) {
            criteria.add(Restrictions.like("name", param));
        }
        if (!(param = (String) criterionParameters.get("countryLike")).equals("")) {
            criteria.add(Restrictions.like("location.country", param));
        }
        if (!(param = (String) criterionParameters.get("cityLike")).equals("")) {
            criteria.add(Restrictions.like("location.city", param));
        }
        if (!(param = (String) criterionParameters.get("streetLike")).equals("")) {
            criteria.add(Restrictions.like("location.street", param));
        }
        if (!(param = (String) criterionParameters.get("buildingLike")).equals("")) {
            criteria.add(Restrictions.like("location.building", param));
        }
//        DetachedCriteria avg = DetachedCriteria.forClass(Rating.class, "r")
//                .setProjection(Projections.avg("r.value"))
//                .add(Restrictions.eq("r.shop", "s"));
//        double temp;
//        if ((temp = (Double) criterionParameters.get("maxRating")) != 0) {
//            criteria.setReadOnly(true);
//            criteria.add(Subqueries.gt(temp, avg));
////            criteria.add(Restrictions.sqlRestriction(
////                    "(SELECT * avg(r.value) FROM rating r WHERE s.id = r.shop_id) between(?, ?)", String.valueOf(temp),));
//        }
//        if ((temp = (Double) criterionParameters.get("minRating")) != 5) {
//            criteria.setReadOnly(true);
//            criteria.add(Subqueries.lt(temp, avg));
//        }

        criteria.setResultTransformer(new AliasToBeanResultTransformer(Shop.class));
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Shop update(Shop object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
        return object;
    }

    @Override
    @Transactional
    public boolean delete(Shop object) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Shop where id = :id");
        query.setParameter("id", object.getId());
        int res = query.executeUpdate();
        return res == 1;
    }
}
