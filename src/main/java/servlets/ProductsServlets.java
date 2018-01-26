package servlets;

import org.apache.log4j.Logger;
import service.ServiceProducts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductsServlets extends HttpServlet {
    final static Logger logger = Logger.getLogger(ProductsServlets.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        ServiceProducts serviceProducts = new ServiceProducts();
        req.setAttribute("productList", serviceProducts.getAll());
        try {
            req.getRequestDispatcher("/products.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }
}
