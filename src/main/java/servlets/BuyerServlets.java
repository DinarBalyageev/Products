package servlets;

import service.ServiceBuyer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BuyerServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServiceBuyer serviceBuyer = new ServiceBuyer();
        resp.getWriter().println("Buyer");
        for (String string : serviceBuyer.getAll())
            resp.getWriter().println(string);
    }
}
