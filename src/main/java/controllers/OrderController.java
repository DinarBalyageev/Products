package controllers;


import POJO.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.OrderStatusService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderStatusService orderStatusService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView getOrder(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderStatus> orderStatusList = orderStatusService.getList(session.getAttribute("user").toString());
        modelAndView.addObject("orderStatusList", orderStatusList);
        return modelAndView;
    }

}
