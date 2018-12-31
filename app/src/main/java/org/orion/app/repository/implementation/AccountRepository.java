package org.orion.app.repository.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orion.app.entity.Account;
import org.orion.app.repository.contract.IAccountRepository;

public class AccountRepository implements IAccountRepository{
    private SessionFactory sessionFactory;
    public AccountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Account save(Account account){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
        session.close();
        return account;
    }

    @Override
    public Account findById(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Account account  = session.load(Account.class , id);
        return account;
    }

    @Override
    public Account findByEmail(String email) {
        return null;
    }
}
