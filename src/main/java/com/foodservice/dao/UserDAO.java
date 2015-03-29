package com.foodservice.dao;

public interface UserDAO<T, V> extends CRUD<T, V>{
    public T getByEmail(String email);
}
