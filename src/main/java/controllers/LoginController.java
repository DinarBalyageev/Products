package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import service.LoginService;

@SessionAttributes("user")
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        return null;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView postLogin(@RequestParam(value = "userName") String login,
                                  @RequestParam(value = "userPassword") String password,
                                  Model model) {
        ModelAndView modelAndView = new ModelAndView();
        LoginService loginService = new LoginService();
        if (loginService.autorization(login, password) == true) {
            modelAndView.addObject("user", login);
            modelAndView.setViewName("redirect:products");
        }   else model.addAttribute("error", "invalidauth");
        return modelAndView;
    }

}
