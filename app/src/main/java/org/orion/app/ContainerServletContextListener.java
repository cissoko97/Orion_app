package org.orion.app;

import org.hibernate.SessionFactory;
import org.orion.app.component.contract.IPasswordEncoder;
import org.orion.app.component.implementation.SHA256PasswordEncoder;
import org.orion.app.controller.AuthenticationController;
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
        //Recuperation du servlet context
        ServletContext servletContext = sce.getServletContext();
        HibernateMySqlConfiguration configuration = new HibernateMySqlConfiguration();

        //configuration de l'unit de persistance
        configuration.configure();
        configuration.addClass(Account.class);
        configuration.buildSessionFactory();

        //Extraction du session factorie
        SessionFactory sessionFactory = configuration.sessionFactory();
        
        //Creation des objets à passer dans le servelt contexte
        IPasswordEncoder sha256PasswordEncoder = new SHA256PasswordEncoder();
        AccountRepository accountRepository = new AccountRepository(sessionFactory);

        //servletContext.setAttribute("sessionFactory", sessionFactory);
        servletContext.setAttribute("accountRepository", accountRepository);
        servletContext.setAttribute("passwordEncoder", sha256PasswordEncoder);
        servletContext.setAttribute("authenticationController", new AuthenticationController(accountRepository, sha256PasswordEncoder));
    }
}
