package io.codeagainsthumanity.controllers;

import io.codeagainsthumanity.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class DecksController {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    BlackCardRepository blackCardRepository;
    @Autowired
    WhiteCardRepository whiteCardRepository;

    @PostMapping("/playWhiteCard")
    public RedirectView playerWhiteCard(double gameCode, Principal p, String whitecard){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());


        //

//        Game gameToJoin = gameRepository.findByGameCode(gameCode);
//        gameToJoin.addPlayer(user);
//        //use method to add the method to the users list of games
//        user.addToMyGames(gameToJoin);
//        //save databases
//        gameRepository.save(gameToJoin);
//        //applicationUserRepository.save(user);

        return new RedirectView("/game/" + gameCode);
    }


    @PostMapping("/judgeWhiteCard")
    public RedirectView judgeWhiteCard(double gameCode, Principal p, String winner){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());


        //passing from html we get a maybe string? of some value from the form,
        //wc1 through wc7.  the name on a radio button is shared, but id unique.

        //the game owns the decks, active and previous cards, variables.


//        gameToJoin.addPlayer(user);
//        //use method to add the method to the users list of games
//        user.addToMyGames(gameToJoin);
//        //save databases
//        gameRepository.save(gameToJoin);
        //applicationUserRepository.save(user);

        return new RedirectView("/game/" + gameCode);
    }
}
