package org.orion.app.controller;

import org.orion.app.component.contract.IPasswordEncoder;
import org.orion.app.entity.Account;
import org.orion.app.model.LoginModel;
import org.orion.app.repository.contract.IAccountRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class AuthenticationController {
    private IAccountRepository accountRepository;
    private IPasswordEncoder passwordEncoder;

    public AuthenticationController(IAccountRepository accountRepository, IPasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void loginFrom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/authentication/login.jsp").forward(request, response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginModel model = new LoginModel();
        model.setEmail(request.getParameter("email"));
        model.setPassword(request.getParameter("password"));
        Map<String, String> fieldErrors = new HashMap<>();

        // Les erreurs et les données du formulaire directement mis dans la requete
        // Celà ne pose pas de problème meme s'il y'a pas d'erreurs

        request.setAttribute("model", model);
        request.setAttribute("errors", fieldErrors);
        if (model.getEmail() == null || model.getEmail().trim().length() == 0) {
            fieldErrors.put("email", "Veuillez renseigner une adresse électronique");
        } else if (!model.getEmail().matches("[̂a-z0-9._-]+@[a-z0-9._-]+\\.[a-z]{2,6}$")) {
            fieldErrors.put("email", "Adresse électronique incorrecte");
        }
        if (model.getPassword() == null || model.getPassword().trim().length() == 0) {
            fieldErrors.put("password", "Veuillez renseigner un mot de passe");
        } else if (model.getPassword().length() < 6) {
            fieldErrors.put("password", "Le mot de passe doit avoir au moins 6 caractères");
        }
        // S'il y'a des erreurs dans le formulaire
        if (fieldErrors.size() > 0) {
            request.getServletContext().getRequestDispatcher("/WEB-INF/authentication/login.jsp")
                    .forward(request, response);
            // On arrete l'exécution de la méthode
            return;
        }
        Account account;
        //IAccountRepository accountRepository = (IAccountRepository) request.getServletContext().getAttribute("accountRepository");
        try {
            account = accountRepository.findByEmail(model.getEmail());
        } catch (NoSuchElementException ignore) {
            fieldErrors.put("email", "Aucun compte avec cet adresse électronique n'existe");
            request.getServletContext().getRequestDispatcher("/WEB-INF/authentication/login.jsp")
                    .forward(request, response);
            return;
        }
        //IPasswordEncoder passwordEncoder = (IPasswordEncoder) request.getServletContext().getAttribute("passwordEncoder");
        // Si le mot de passe fournit ne correspont pas avec celui qui compte
        if (!passwordEncoder.matches(model.getPassword(), account.getPasswordHash())) {
            fieldErrors.put("password", "Ce mot de passe ne correspond pas à celui du compte");
            request.getServletContext().getRequestDispatcher("/WEB-INF/authentication/login.jsp")
                    .forward(request, response);
            return;
        }
        // On peut maintenant connecter le compte
        request.getSession().setAttribute("account", account);
        response.sendRedirect("/home");
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("account");
        response.sendRedirect("/authentication/login");
    }
}
