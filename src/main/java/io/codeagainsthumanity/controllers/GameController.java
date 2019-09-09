package io.codeagainsthumanity.controllers;

import io.codeagainsthumanity.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    BlackCardRepository blackCardRepository;
    @Autowired
    WhiteCardRepository whiteCardRepository;

    @GetMapping("/allGames")
    public String getAllGames(Principal p, Model m) {
        List<Game> gamesList = gameRepository.findAll();
        System.out.println("GAMES: " + gamesList.toString());
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("gamesList", gamesList);
        m.addAttribute("principalUser", p);
        m.addAttribute("user", user);
        return "allGames";
    }

    @PostMapping("/createGame")
    public RedirectView createGame(Principal p, Model m) throws IOException {
        ApplicationUser gameOwner = applicationUserRepository.findByUsername(p.getName());

        m.addAttribute("user", gameOwner);

        m.addAttribute("principalUser", p);
        double gameCode = Math.random() * 100;
        Game newGame = new Game(gameOwner, gameCode);
        Status status = new Status();
        //use method to add the method to the users list of games
        gameOwner.addToMyGames(newGame);

        m.addAttribute("currentGame", newGame);

        status.setStatusCode(0); //status code for first player is set to 0
//        status.set
        status.setGame(newGame);
        status.setPlayer(gameOwner);
        System.out.println("STATUS CODE: " + status.getStatusCode());


        //set users boolean submitted to true, because they are the judge.
        Boolean bool = true;
        newGame.setBooleanToSubmitted( gameOwner.getId(),bool);
        //give the user a hand
        List<String> hand = new ArrayList<>();
        for (int i = 0 ; i < 7 ; i ++){
            WhiteCard wc = newGame.randomWhiteCard();
            hand.add(wc.getText());
        }
        //then push hand into games hashmap, called hands.
        newGame.getHands().put(gameOwner.getId(),hand);

        //save databases
        gameRepository.save(newGame);
        applicationUserRepository.save(gameOwner);
        statusRepository.save(status);

        return new RedirectView("/game/" + gameCode);
    }

    @GetMapping ("/game/{gameCode}")
    public String getGame(Principal p, Model m, @PathVariable double gameCode){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("user", user);
        m.addAttribute("currentGame", gameRepository.findByGameCode(gameCode));

        m.addAttribute("principalUser", p);
        return "gameroom";
    }

}
