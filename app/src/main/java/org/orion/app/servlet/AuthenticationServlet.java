package org.orion.app.servlet;

import org.orion.app.component.contract.IPasswordEncoder;
import org.orion.app.controller.AuthenticationController;
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
        AuthenticationController controller = (AuthenticationController)
                getServletContext().
                        getAttribute("authenticationController");
        if (req.getMethod().equals("GET") && req.getPathInfo().equals("/login")) {
            controller.loginFrom(req, resp);
        } else if (req.getMethod().equals("POST") && req.getPathInfo().equals("/login")) {
            controller.login(req, resp);
        } else if (req.getMethod().equals("POST") && req.getPathInfo().equals("/logout")) {
            controller.logout(req, resp);
        } else {
            throw new RuntimeException("L'action ciblée est introuvable");
        }
    }
}
