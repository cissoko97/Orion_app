package org.orion.app.persistance.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.orion.app.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class HibernateSqlConfigurationTest {
    @Test
    void save() {
        HibernateMySqlConfiguration configuration = new HibernateMySqlConfiguration();
        configuration.configure();
        configuration.addClass(User.class);
        configuration.buildSessionFactory();
        User user = new User();
        user.setName("Chendjou");
        user.setSurname("Caleb");
        SessionFactory sessionFactory = configuration.sessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("New user id: " + user.getId());
    }

}