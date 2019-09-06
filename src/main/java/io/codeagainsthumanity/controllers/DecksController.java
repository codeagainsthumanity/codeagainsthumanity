package io.codeagainsthumanity.controllers;

import io.codeagainsthumanity.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
        Game game = gameRepository.findByGameCode(gameCode);
        // currentJudge selects fave wc from options
        // selected card becomes previousWhite

        // currentBlack becomes previousBlack
        //draw new Blackcard
        game.updateBlackCardToBeJudgedAndPreviousBlack();

        //submitted now equals false
        Boolean bool = false;
        game.setBooleanToSubmitted(user.getId(), bool);

           //give the user a hand
        List<String> hand = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            WhiteCard wc = game.randomWhiteCard();
            hand.add(wc.getText());
        }
        //then push hand into games hashmap, called hands.
        game.getHands().put(user.getId(), hand);

        game.swapJudge();
        gameRepository.save(game);

        return new RedirectView(("/game/" + gameCode));
    }
    //passing from html we get a maybe string? of some value from the form,
    //wc1 through wc7.  the name on a radio button is shared, but id unique.

    //the game owns the decks, active and previous cards, variables.

//        gameToJoin.addPlayer(user);
//        //use method to add the method to the users list of games
//        user.addToMyGames(gameToJoin);
//        //save databases
//        gameRepository.save(gameToJoin);
    //applicationUserRepository.save(user);

    @PostMapping("/playWhiteCard")
    public RedirectView playerWhiteCard(String gameCode, Principal p, String choice) {
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());

        //cast string to double
        double dgc = Double.parseDouble(gameCode);
        System.out.println("DGC: " + dgc);
        //find game by double game code
        Game game = gameRepository.findByGameCode(dgc);

        //player selects white card and hits submit
        //whitecard is added to the toBeJudge List
        WhiteCard wc = new WhiteCard(choice);
        game.getToBeJudged().add(wc);
        // player hand submitted is true and their hand/form is hidden until judged
        Boolean bool = true;
        game.setBooleanToSubmitted(user.getId(), bool);

        //added to avoid an error.
        whiteCardRepository.save(wc);

        //user draws new card
        List<String> hand = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String cardString = game.getHands().get(user.getId()).get(i);
            if (!cardString.equals(choice)) {
                hand.add(cardString);
            } else {
                //player drops that card from their hand, grabs new.
                WhiteCard random = game.randomWhiteCard();
                String randomString = random.getText();
                hand.add(randomString);
            }
        }

        //then push hand into games hashmap, called hands.
        game.getHands().put(user.getId(), hand);
        //save repos
        applicationUserRepository.save(user);
        gameRepository.save(game);

        return new RedirectView("/game/" + gameCode);
    }
}
