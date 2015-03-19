package com.foodservice.businesslogic.user;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "manager_user")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class ManagerUser extends User {

}
