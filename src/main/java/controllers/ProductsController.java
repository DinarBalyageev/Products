package controllers;


import POJO.Products;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.ServiceProducts;

import java.util.*;

@Controller
public class ProductsController {
    private Map<Integer, Integer> session = new HashMap();

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        ServiceProducts serviceProducts = new ServiceProducts();
        ModelAndView modelAndView = new ModelAndView();
        List<Products> productsList = serviceProducts.getAll();
        modelAndView.addObject("productsList", productsList);
        modelAndView.setViewName("products");
        return modelAndView;

    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ModelAndView postLogin(@RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "count") Integer count) {
        session.put(id, count);
        ServiceProducts serviceProducts = new ServiceProducts();
        ModelAndView modelAndView = new ModelAndView();
        List<Products> productsList = serviceProducts.getAll();
        modelAndView.addObject("productsList", productsList);
        modelAndView.setViewName("products");
        for (Map.Entry<Integer, Integer> entry : session.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        return modelAndView;
    }
}
