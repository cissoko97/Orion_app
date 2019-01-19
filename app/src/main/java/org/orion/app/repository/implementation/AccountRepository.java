package org.orion.app.repository.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.orion.app.entity.Account;
import org.orion.app.repository.contract.IAccountRepository;
import org.orion.app.repository.contract.Repository;

import java.util.HashSet;
import java.util.Set;


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
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Account account Where account.email=:email");
        query.setParameter("email" , email);
        Account account = (Account) query.uniqueResult();
        return account;
    }

    @Override
    public Set<Account> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Account account");
        Set<Account> accounts = new HashSet<>(query.list());
        return accounts;
    }
}
