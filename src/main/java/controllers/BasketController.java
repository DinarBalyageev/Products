package controllers;

        import DAO.OrderDao;
        import DAO.OrderDaoImpl;
        import DAO.OrderStatusDao;
        import DAO.OrderStatusDaoImpl;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.servlet.ModelAndView;

        import javax.servlet.http.HttpSession;

@Controller
public class BasketController {
    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ModelAndView getBasket() {
        return null;
    }


    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public ModelAndView postBasket(@RequestParam(value="target")String target, HttpSession session) {
        System.out.println(target);
        ModelAndView modelAndView = new ModelAndView();
        if (target.equals("ok_order")){
            OrderDao orderDao = new OrderDaoImpl();
            OrderStatusDao orderStatusDao = new OrderStatusDaoImpl();
            orderStatusDao.add(1,"new");
            session.setAttribute("basketList", null);
            session.setAttribute("sessionProductsList", null);
            //modelAndView.setViewName("redirect:products");
        }

        if (target.equals("cancel_order")){
            session.setAttribute("basketList", null);
            session.setAttribute("sessionProductsList", null);
            modelAndView.setViewName("redirect:products");
        }
        return modelAndView;
    }
}