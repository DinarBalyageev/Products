package controllers;


import POJO.Products;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.ServiceProducts;

import java.util.*;

import static oracle.jrockit.jfr.events.Bits.intValue;

@SessionAttributes({"sessionProductsList", "basketList", "summa"})
@Controller
public class ProductsController {
    private HashMap<Integer, Integer> session = new HashMap();

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
                                  @RequestParam(value = "count") Integer count,
    @RequestParam(value = "target") String target) {
        ModelAndView modelAndView = new ModelAndView();
        if(target.equals("products")){
        session.put(id, count);
        ServiceProducts serviceProducts = new ServiceProducts();
        List<Products> productsList = serviceProducts.getAll();
        modelAndView.addObject("sessionProductsList", session);
        modelAndView.addObject("productsList", productsList);
        modelAndView.setViewName("products");}
        if(target.equals("basket")) {
            Float summa = 0.0F;
            List<Products> basketList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : session.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                ServiceProducts serviceProducts = new ServiceProducts();
                Products basket = serviceProducts.findId(entry.getKey());
                basket.setCount(entry.getValue());
                if (basket.getCount()!=0) basketList.add(basket);
                summa+=entry.getValue()*basket.getPrice();
            }
            modelAndView.addObject("summa", summa);
            modelAndView.addObject("basketList", basketList);
            modelAndView.setViewName("redirect:basket");
            session.clear();
            summa = 0.0F;
            basketList = null;
        }

        return modelAndView;
    }

}
