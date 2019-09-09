package io.codeagainsthumanity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getRoot(Principal p, Model m){
        m.addAttribute("principalUser", p);
        return "root";
    }

    @GetMapping ("/login")
    public String getLoginPage(Principal p, Model m){
        m.addAttribute("principalUser", p);
        return "login";
    }

    @GetMapping ("/registration")
    public String getRegistraionPage(Principal p, Model m){
        m.addAttribute("principalUser", p);
        return "registration";

    }

    @GetMapping("/aboutUs")
    public String getAboutUsPage() {
        return "about";
    }

}
