package controllers;


import POJO.Products;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.LoginService;
import service.ServiceProducts;

import java.util.*;

@Controller
public class ProductsController {

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        ServiceProducts serviceProducts = new ServiceProducts();
        ModelAndView modelAndView = new ModelAndView();
        List<Products> productsList = serviceProducts.getAll();
        modelAndView.addObject("productsList",productsList);
        modelAndView.setViewName("products");
        return modelAndView;

    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ModelAndView postLogin() {
        return null;
    }

}
