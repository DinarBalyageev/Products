package controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.LoginService;
import service.ServiceProducts;

import java.util.Collection;
import java.util.Map;

@Controller
public class ProductsController {

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Model getLogin() {
        ServiceProducts serviceProducts = new ServiceProducts();

//        req.setAttribute("productList", serviceProducts.getAll());
//        try {
//            req.getRequestDispatcher("/products.jsp").forward(req, resp);
//        } catch (ServletException | IOException e) {
//            logger.error(e);
//        }
        return null;
    }


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ModelAndView postLogin() {
        return null;
    }

}
