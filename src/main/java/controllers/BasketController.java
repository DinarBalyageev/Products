package controllers;

import POJO.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.LoginService;
import service.OrderService;
import service.OrderStatusService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BasketController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ModelAndView getBasket() {
        return null;
    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public ModelAndView postBasket(@RequestParam(value = "target") String target, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("basket");
        if (target.equals("ok_order")) {
            List<Products> list = (List<Products>) session.getAttribute("basketList");
            if (list != null) {
                int idOrder = orderStatusService.add(loginService.getId(session.getAttribute("user").toString()));
                for (Products products : list) {
                    orderService.add(idOrder, products.getId(), products.getCount());
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