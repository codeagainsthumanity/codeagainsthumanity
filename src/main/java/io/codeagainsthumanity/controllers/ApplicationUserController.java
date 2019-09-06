package io.codeagainsthumanity.controllers;

import io.codeagainsthumanity.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationUserController {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    StatusRepository statusRepository;

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

    @GetMapping ("/profile")
    public String getMyProfile (Principal p, Model m) {
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());

        m.addAttribute("user", user);
        m.addAttribute("principalUser", p);

        return "profile";
    }

    @GetMapping("/profiles")
    public String getAllProfiles(Principal p, Model m) {
        List<ApplicationUser> users = applicationUserRepository.findAll();
        m.addAttribute("users", users);
        m.addAttribute("principalUser", p);
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("user", user);
        return "profiles";
    }

    @PostMapping ("/createUser")
    public RedirectView createUser (String username, String password, String fullName){
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), fullName);
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/profile");
    }



    @PostMapping ("/joinGame")
    public RedirectView joinGame(double gameCode, Principal p){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        Game gameToJoin = gameRepository.findByGameCode(gameCode);
        gameToJoin.addPlayer(user);

        Status newStatus = new Status();
        //use method to add the method to the users list of games
        user.addToMyGames(gameToJoin);

        //give the user a hand
        List<String> hand = new ArrayList<>();
        for (int i = 0 ; i < 7 ; i ++){
            WhiteCard wc = gameToJoin.randomWhiteCard();
            hand.add(wc.getText());
        }
        //then push hand into games hashmap, called hands.
        gameToJoin.getHands().put(user.getId(),hand);

        newStatus.setStatusCode(1); //status code for a joining user is 1
//        status.set
        newStatus.setGame(gameToJoin);
        newStatus.setPlayer(user);
        //save databases
        gameRepository.save(gameToJoin);
        statusRepository.save(newStatus);
        //applicationUserRepository.save(user);

        return new RedirectView("/game/" + gameToJoin.getGameCode());
    }


}
