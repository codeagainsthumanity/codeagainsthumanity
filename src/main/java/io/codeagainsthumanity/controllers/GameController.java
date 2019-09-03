package io.codeagainsthumanity.controllers;

import io.codeagainsthumanity.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class GameController {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    DeckRepository deckRepository;
    @Autowired
    BlackCardRepository blackCardRepository;
    @Autowired
    WhiteCardRepository whiteCardRepository;

    @GetMapping("/myGames")
    public String getAllGames(Principal p, Model m) {
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        List<Game> myGameInstances = new LinkedList<>();
        m.addAttribute("games", myGameInstances);
        return "gameroom";
    }

    @GetMapping("/gameroom")
    public String getGameRoom(Principal p, Model m){
            m.addAttribute("principalUser", p);
            return "gameroom";
    }


}
