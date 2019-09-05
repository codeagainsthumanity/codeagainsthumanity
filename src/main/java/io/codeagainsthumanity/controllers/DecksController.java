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


    @PostMapping("/judgeWhiteCard")
    public RedirectView judgeWhiteCard(double gameCode, Principal p, String choice) {
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        // currentJudge selects fave wc from options
        // selected card becomes previousWhite
        // currentBlack becomes previousBlack
        //draw new Blackcard
        //submitted now equals false
        Game game = gameRepository.findByGameCode(gameCode);
        game.swapJudge();
        gameRepository.save(game);
        return new RedirectView(("/game/" + gameCode));


        //passing from html we get a maybe string? of some value from the form,
        //wc1 through wc7

//        gameToJoin.addPlayer(user);
//        //use method to add the method to the users list of games
//        user.addToMyGames(gameToJoin);
//        //save databases
//        gameRepository.save(gameToJoin);
        //applicationUserRepository.save(user);


    }

    @PostMapping("/playWhiteCard")
    public RedirectView playerWhiteCard(double gameCode, Principal p, String choice) {
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        Game game = gameRepository.findByGameCode(gameCode);

        //player selects white card and hits submit
        //whitecard is added to the toBeJudge List
        // player hand submitted is true and their hand/form is hidden until judged
        // when submitted is false hand/form is shown

        return new RedirectView("/game/" + gameCode);
    }
}
