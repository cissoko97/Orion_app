package org.orion.app.repository.Interface;

import java.util.Set;

public interface IRepository<T,U> {
    T save(T t);
    Set <T> findAll();
    T update(T t);
    Boolean delete(T t);
    T findById(U id);
}
