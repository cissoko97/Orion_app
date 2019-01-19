package org.orion.app.repository.contract;

import org.hibernate.SessionFactory;
import org.orion.app.entity.Account;
import org.orion.app.repository.Interface.IRepository;

import java.util.Set;

public abstract class Repository<T, U> implements IRepository<T, U> {

    private SessionFactory sessionFactory;
    private Class<T> type;

    public Repository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T save(T t) {
        return null;
    }

    @Override
    public Set<T> findAll() {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public Boolean delete(T t) {
        return null;
    }

    @Override
    public T findById(U id) {
        return null;
    }
}
