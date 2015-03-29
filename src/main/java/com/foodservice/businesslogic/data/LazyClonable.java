package com.foodservice.businesslogic.data;

/**
 * Entities should implement this interface to provide lazy cloning
 * @param <E> entity type
 */
public interface LazyClonable<E> {

    public E clone();
}
