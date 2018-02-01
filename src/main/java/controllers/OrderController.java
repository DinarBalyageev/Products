package controllers;


import DAO.OrderStatusDao;
import DAO.OrderStatusDaoImpl;
import DAO.UserDao;
import DAO.UserDaoImpl;
import POJO.OrderStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView getOrder(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        OrderService orderService = new OrderService();
        List<OrderStatus> orderStatusList = orderService.getList(session.getAttribute("user").toString());
        modelAndView.addObject("orderStatusList", orderStatusList);
        return modelAndView;
    }

}
