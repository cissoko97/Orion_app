package org.orion.app.persistance.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.orion.app.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class HibernateH2ConfigurationTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    void Save() {
        HibernateH2Configuration configuration = new HibernateH2Configuration();
        configuration.configure();
        configuration.addClass(User.class);
        configuration.buildSessionFactory();
        User user = new User();
        user.setName("Issac");
        user.setSurname("Newton");
        SessionFactory sessionFactory = configuration.sessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        logger.info("New user id: " + user.getId());
    }


}