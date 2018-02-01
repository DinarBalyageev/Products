package controllers;

import DAO.*;
import POJO.Products;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BasketController {
    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ModelAndView getBasket() {
        return null;
    }


    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public ModelAndView postBasket(@RequestParam(value = "target") String target, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("basket");
        if (target.equals("ok_order")) {
            UserDao userDao = new UserDaoImpl();
            OrderDao orderDao = new OrderDaoImpl();
            OrderStatusDao orderStatusDao = new OrderStatusDaoImpl();
            List<Products> list = (List<Products>) session.getAttribute("basketList");
            if (list != null) {
                int idOrder = orderStatusDao.add(userDao.getId(session.getAttribute("user").toString()), "new");
                for (Products products : list) {
                    orderDao.add(idOrder, products.getId(), products.getCount());
                }
                session.setAttribute("basketList", null);
                session.setAttribute("sessionProductsList", null);
                session.setAttribute("summa", 0.0F);
                modelAndView.setViewName("redirect:products");
            }
        }
        if (target.equals("cancel_order")) {
            session.setAttribute("basketList", null);
            session.setAttribute("sessionProductsList", null);
            session.setAttribute("summa", 0.0F);
            modelAndView.setViewName("redirect:products");
        }
        return modelAndView;
    }
}