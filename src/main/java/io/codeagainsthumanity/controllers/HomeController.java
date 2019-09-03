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

    @GetMapping("/bootstrap")
    public String getBootstrap(Principal p, Model m){
        m.addAttribute("principalUser", p);
        return "bootstrap";
    }

}
