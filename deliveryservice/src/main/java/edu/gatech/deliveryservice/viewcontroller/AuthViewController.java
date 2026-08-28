package edu.gatech.deliveryservice.viewcontroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")

public class AuthViewController {
    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("index") // this is an example of the function pages we could direct customer/pilot to
    public String getIndexMapping() {
        return "admin_platform";
    }

    @GetMapping("logout")
    public String getLogoutView() {
        return "logout";
    }

    @GetMapping("make_customer")
    public String makeNewCustomer() {
        return "make_customer";
    }



    }





