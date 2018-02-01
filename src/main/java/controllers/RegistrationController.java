package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.RegistrationService;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        return null;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView postLogin(@RequestParam(value = "userName") String login,
                                  @RequestParam(value = "userPassword") String password,
                                  @RequestParam(value = "retryPassword") String retryPassword,
                                  @RequestParam(value = "firstname") String firstName,
                                  @RequestParam(value = "name") String name,
                                  @RequestParam(value = "lastname") String lastName,
                                  @RequestParam(value = "address") String address,
                                  Model model) {
        ModelAndView modelAndView = new ModelAndView();
        RegistrationService registrationService = new RegistrationService();
        if (registrationService.identification(login)) {
            model.addAttribute("error", "invalidlogin");
        } else if (!password.equals(retryPassword)) {
            model.addAttribute("error", "passworderror");
        } else {

            registrationService.registration(login, password,firstName,name,lastName,address);
            modelAndView.setViewName("redirect:login");
            modelAndView.addObject("error", "enter");
            model.addAttribute("error", "enter");
        }
        return modelAndView;
    }

}
