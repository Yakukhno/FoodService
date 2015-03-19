package com.foodservice.dao;


public interface CRUD <T,V> {

    public T create(T object);
    public T get(V object);
    public T update(T object);
    public boolean delete(T object);
}
