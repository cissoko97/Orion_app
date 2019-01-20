package org.orion.app.controller;

import org.orion.app.component.contract.IPasswordEncoder;
import org.orion.app.entity.Account;
import org.orion.app.model.LoginModel;
import org.orion.app.repository.contract.IAccountRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/authentication/*")
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET") && req.getPathInfo().equals("/login")) {
            getServletContext().getRequestDispatcher("/WEB-INF/authentication/login.jsp")
                    .forward(req, resp);
        } else if (req.getMethod().equals("POST") && req.getPathInfo().equals("/login")) {
            LoginModel model = new LoginModel();
            model.setEmail(req.getParameter("email"));
            model.setPassword(req.getParameter("password"));
            Map<String, String> fieldErrors = new HashMap<>();
            // Les erreurs et les données du formulaire directement mis dans la requete
            // Celà ne pose pas de problème meme s'il y'a pas d'erreurs
            req.setAttribute("model", model);
            req.setAttribute("errors", fieldErrors);
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
                getServletContext().getRequestDispatcher("/WEB-INF/authentication/login.jsp")
                        .forward(req, resp);
                // On arrete l'exécution de la méthode
                return;
            }
            Account account;
            IAccountRepository accountRepository = (IAccountRepository) getServletContext()
                    .getAttribute("accountRepository");
            try {
                account = accountRepository.findByEmail(model.getEmail());
            } catch (NoSuchElementException ignore) {
                fieldErrors.put("email", "Aucun compte avec cet adresse électronique n'existe");
                getServletContext().getRequestDispatcher("/WEB-INF/authentication/login.jsp")
                        .forward(req, resp);
                return;
            }
            IPasswordEncoder passwordEncoder = (IPasswordEncoder) getServletContext()
                    .getAttribute("passwordEncoder");
            // Si le mot de passe fournit ne correspont pas avec celui qui compte
            if (!passwordEncoder.matches(model.getPassword(), account.getPasswordHash())) {
                fieldErrors.put("password", "Ce mot de passe ne correspond pas à celui du compte");
                getServletContext().getRequestDispatcher("/WEB-INF/authentication/login.jsp")
                        .forward(req, resp);
                return;
            }
            // On peut maintenant connecter le compte
            req.getSession().setAttribute("account", account);
            resp.sendRedirect("/home");
        } else if (req.getMethod().equals("POST") && req.getPathInfo().equals("/logout")) {
            req.getSession().removeAttribute("account");
            resp.sendRedirect("/authentication/login");
        } else {
            throw new RuntimeException("L'action ciblée est introuvable");
        }
    }
}
