package org.orion.app.servlet;

import org.orion.app.component.contract.IPasswordEncoder;
import org.orion.app.entity.Account;
import org.orion.app.model.RegistrationModel;
import org.orion.app.repository.contract.IAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Redirection vers la page D'acceuil
        req.getRequestDispatcher("/WEB-INF/account/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperation de l'objet Account Repository depuis le servletContext
        IAccountRepository accountRepository = (IAccountRepository) getServletContext().getAttribute("accountRepository");

        //Recuperation de l'objet passwordEncoder depuis le serveltContext
        IPasswordEncoder passwordEncoder = (IPasswordEncoder) getServletContext().getAttribute("passwordEncoder");
        //Recupération des parametres de la requete
        RegistrationModel model = new RegistrationModel();
        model.setName(req.getParameter("name"));
        model.setSurname(req.getParameter("surname"));
        model.setEmail(req.getParameter("email"));
        model.setPhone(req.getParameter("phone"));
        model.setPassword(req.getParameter("password"));
        model.setPasswordMatch(req.getParameter("passwordMatch"));

        //Dictionnaire pour la recupération des erreurs
        Map<String, String> errors = new HashMap<>();

        //Validation des données avant la creation du compte
        if (model.getName() == null || model.getName().trim().length() == 0) {
            errors.put("name", "Veillez renseignez votre nom");
        } else if (model.getName().length() < 4) {
            errors.put("name", "Votre nom doit contenir au moins 4 lettres");
        } else if (!model.getName().matches("^[a-zA-Z àâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ]+$"))
            errors.put("name", "Votre nom ne doit pas contenir des symboles incorrects");

        if (model.getSurname() == null || model.getSurname().trim().length() == 0) {
            errors.put("surname", "Veillez renseignez votre prenom");
        } else if (model.getSurname().length() < 4) {
            errors.put("surname", "Votre prenom doit contenir au moins 4 lettres");
        } else if (!model.getSurname().matches("^[a-zA-Z àâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ]+$"))
            errors.put("surname", "Votre nom ne doit pas contenir des symboles incorrects");

        if (model.getPhone() == null || model.getPhone().trim().length() == 0) {
            errors.put("phone", "Veillez renseigner votre numero de telephone");
        } else if (model.getPhone().length() < 9) {
            errors.put("phone", "Votre numero de telephome doit contenir qu moins 9 chiffres");
        } else if (!model.getPhone().matches("[0-9]+")) {
            errors.put("phone", "votre numero ne doit contenir que des lettres");
        }

        if (model.getEmail() == null || model.getEmail().trim().length() == 0) {
            errors.put("email", "Votre email ne doit pas etre vide");
        } else if (!model.getEmail().matches("[a-z0-9._-]+@[a-z0-9._-]+\\.[a-z]{2,6}")) {
            errors.put("email", "Votre email n'a pqs um format valide");
        } else if (!(accountRepository.findByEmail(model.getEmail()) == null))
            errors.put("email", "Adresse email déja utilisé");

        if (model.getPassword() == null || model.getPassword().trim().length() == 0)
            errors.put("password", "Votre mot de password ne peut etre vide");
        else if (model.getPassword().trim().length() < 6)
            errors.put("password", "Votre mot de passe doit contenir au moins 6 caracteres");

        if (model.getPasswordMatch() == null || model.getPasswordMatch().trim().length() == 0)
            errors.put("passwordMatch", "le champ confirmation du mot de passe ne peut etre vide");
        else if (!model.getPasswordMatch().equals(model.getPassword()))
            errors.put("passwordMatch", "les deux mots de passes doivent être identiques");

        //Verification de l'existance des erreurs
        if (errors.size() > 0) {
            req.setAttribute("model", model);
            req.setAttribute("errors", errors);
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/account/registration.jsp")
                    .forward(req, resp);
            return;

        } else {
            //Construstion de l'objet Account
            Account account = new Account();
            account.setName(model.getName());
            account.setSurname(model.getSurname());
            account.setEmail(model.getEmail());
            account.setPhone(model.getPhone());
            account.setPasswordHash(passwordEncoder.encodePassword(model.getPasswordMatch()));
            account.setRedistration(new Date());
            //Persistance de l'objet Account
            accountRepository.save(account);
            logger.info("User id is " + account.getId());
            resp.sendRedirect("/home" + "?accountId=" + account.getId());
        }

    }
}
