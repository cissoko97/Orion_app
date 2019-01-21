package org.orion.app.servlet;

import org.orion.app.entity.Account;
import org.orion.app.repository.contract.IAccountRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = "/account/list")
public class AccountListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IAccountRepository accountRepository = (IAccountRepository) getServletContext().getAttribute("accountRepository");

        Set<Account> accounts = accountRepository.findAll();
        req.setAttribute("accounts", accounts);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/account/list.jsp")
                .forward(req, resp);
        return;

    }
}
