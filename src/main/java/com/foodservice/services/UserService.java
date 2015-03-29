package com.foodservice.services;

import com.foodservice.businesslogic.user.User;

/**
 * Created by Grigoriy on 3/11/2015.
 */
public interface UserService<E extends User> {
    
    public E create(E object);
    public E get(Integer key);
    public E getByEmail(String email);
    public E update(E object);
    public boolean delete(E object);
}
