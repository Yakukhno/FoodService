package com.kpi.education.services;

import com.kpi.education.businesslogic.user.User;

/**
 * Created by Grigoriy on 3/11/2015.
 */
public interface UserService<E extends User> {
    
    public E create(E object);
    public E get(Integer key);
    public E getByEmain(String email);
//    public E getMainAttributes(Integer key);
    public E update(E object);
    public boolean delete(E object);
}
