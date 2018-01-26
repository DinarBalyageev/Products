package servlets;


import org.apache.log4j.Logger;
import service.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationServlets extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegistrationServlets.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("userName");
        String password = req.getParameter("userPassword");
        String retryPassword = req.getParameter("retryPassword");
        RegistrationService registrationService = new RegistrationService();
        if (registrationService.identification(login)) {
            try {
                req.getRequestDispatcher("/registration.jsp?error=invalidlogin").forward(req, resp);
            } catch (ServletException e) {
                logger.error(e);
            }
        } else if (!password.equals(retryPassword)) {
            try {
                req.getRequestDispatcher("/registration.jsp?error=passworderror").forward(req, resp);
            } catch (ServletException e) {
                logger.error(e);
            }
        } else {registrationService.registration(login, password);
            try {
                req.getRequestDispatcher("/login.jsp?error=enter").forward(req, resp);
            } catch (ServletException e) {
                logger.error(e);
            }}
    }
}
