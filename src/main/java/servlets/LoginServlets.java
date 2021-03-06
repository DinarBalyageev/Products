package servlets;

import service.LoginService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("userName");
        String password = req.getParameter("userPassword");

        LoginService loginService = new LoginService();
        try {
            if (loginService.autorization(login, password) == true) {
                req.getSession().setAttribute("login", login);
                resp.sendRedirect("/order/products");
            } else req.getRequestDispatcher("/login.jsp?error=invalidauth").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
