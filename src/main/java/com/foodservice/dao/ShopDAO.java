package com.foodservice.dao;

import com.foodservice.businesslogic.Rating;
import com.foodservice.businesslogic.Shop;
import org.hibernate.*;
import org.hibernate.criterion.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class ShopDAO implements CRUD<Shop, Integer> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Shop create(Shop object) {
        Session session = sessionFactory.getCurrentSession();
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
    public List<Shop> getByShopAdminUserID(Integer shopAdminUserId) {
        Session session = sessionFactory.getCurrentSession();
        List<Shop> shops = session.createQuery(
                "from Shop s where s.shopAdminUserId = :shopAdminUserId")
                .setParameter("shopAdminUserId", shopAdminUserId).list();
        return shops;
    }
    
    @Transactional(readOnly = true)
    public List<Shop> getByCriterion(Map<String, Object> criterionParameters) {
        Session session = sessionFactory.getCurrentSession();

//        System.out.println("nameLike" + criterionParameters.get("nameLike"));
//        System.out.println("minRating"+ criterionParameters.get("minRating"));
//        System.out.println("maxRating"+ criterionParameters.get("maxRating"));
//        System.out.println("countryLike"+ criterionParameters.get("countryLike"));
//        System.out.println("cityLike"+ criterionParameters.get("cityLike"));
//        System.out.println("streetLike"+ criterionParameters.get("streetLike"));
//        System.out.println("buildingLike"+ criterionParameters.get("buildingLike"));

        Criteria criteria = session.createCriteria(Shop.class, "s");
        criteria.setReadOnly(true);
//        ProjectionList projList = Projections.projectionList();
//        projList.add(Property.forName("id").as("id"));
//        projList.add(Property.forName("name").as("name"));
//        projList.add(Property.forName("location.country").as("country"));
//        projList.add(Property.forName("location.city").as("city"));
//        projList.add(Property.forName("location.street").as("street"));
//        projList.add(Property.forName("location.building").as("building"));
//        projList.add(Projections.property("description").as("description"));
//        criteria.setProjection(projList);
        String param;
        if (!(param = (String) criterionParameters.get("nameLike")).equals("")) {
            criteria.add(Restrictions.ilike("name", "%" + param + "%"));
        }
        if (!(param = (String) criterionParameters.get("countryLike")).equals("")) {
            criteria.add(Restrictions.ilike("location.country", "%" + param + "%"));
        }
        if (!(param = (String) criterionParameters.get("cityLike")).equals("")) {
            criteria.add(Restrictions.ilike("location.city", "%" + param + "%"));
        }
        if (!(param = (String) criterionParameters.get("streetLike")).equals("")) {
            criteria.add(Restrictions.ilike("location.street", "%" + param + "%"));
        }
        if (!(param = (String) criterionParameters.get("buildingLike")).equals("")) {
            criteria.add(Restrictions.ilike("location.building", "%" + param + "%"));
        }

        DetachedCriteria avg = DetachedCriteria.forClass(Rating.class, "r")
                .setProjection(Projections.avg("r.value"))
                .add(Restrictions.eqProperty("r.shopId", "s.id"));
        double temp;
        if ((temp = (Double) criterionParameters.get("maxRating")) != 5) {
            criteria.add(Subqueries.gt(temp, avg));
        }
        if ((temp = (Double) criterionParameters.get("minRating")) != 0) {
            criteria.add(Subqueries.lt(temp, avg));
        }

//        criteria.setResultTransformer(new ResultTransformer() {
//            @Override
//            public Object transformTuple(Object[] tuple, String[] aliases) {
//                Shop shop = new Shop();
//                shop.setId((Integer) tuple[0]);
//                shop.setName((String) tuple[1]);
//                Location location = new Location();
//                    location.setCountry((String) tuple[2]);
//                    location.setCity((String) tuple[3]);
//                    location.setStreet((String) tuple[4]);
//                    location.setBuilding((String) tuple[5]);
//                shop.setLocation(location);
//                shop.setDescription((String) tuple[6]);
//                return shop;
//            }
//
//            @Override
//            public List transformList(List collection) {
//                return collection;
//            }
//        });
        return criteria.list();
    }

    @Override
    public Shop update(Shop object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
        return object;
    }

    @Override
    public boolean delete(Shop object) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Shop s where s.id = :id");
        query.setParameter("id", object.getId());
        int res = query.executeUpdate();
        return res == 1;
    }
}
