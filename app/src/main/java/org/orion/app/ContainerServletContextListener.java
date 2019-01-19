package org.orion.app;

import org.hibernate.SessionFactory;
import org.orion.app.component.implementation.SHA256PasswordEncoder;
import org.orion.app.entity.Account;
import org.orion.app.persistance.configuration.HibernateMySqlConfiguration;
import org.orion.app.repository.implementation.AccountRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContainerServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        HibernateMySqlConfiguration configuration = new HibernateMySqlConfiguration();
        configuration.configure();
        configuration.addClass(Account.class);
        configuration.buildSessionFactory();
        SessionFactory sessionFactory = configuration.sessionFactory();
        AccountRepository accountRepository = new AccountRepository(sessionFactory);
        //servletContext.setAttribute("sessionFactory", sessionFactory);
        servletContext.setAttribute("accountRepository", accountRepository);
        servletContext.setAttribute("passwordEncoder", new SHA256PasswordEncoder());
    }
}
