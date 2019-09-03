package io.codeagainsthumanity.controllers;

import io.codeagainsthumanity.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

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
//        List<Game> myGameInstances = new LinkedList<>();
        m.addAttribute("games", gameRepository.findAll());
        return "gameroom";
    }

    @GetMapping("/createGame")
        public String getGameRoom () {
            return "gameRoom";
        }


    @PostMapping("/createGame")
    public RedirectView createGame(Principal p) {
        ApplicationUser gameOwner = applicationUserRepository.findByUsername(p.getName());
        double gameCode = Math.random() * 100;
        Game newGame = new Game(gameOwner, gameCode);
        gameRepository.save(newGame);

        return new RedirectView("/game/" + gameCode);
    }

    @GetMapping("/gameroom")
    public String getGameRoom(Principal p, Model m) {
        m.addAttribute("principalUser", p);
        return "gameroom";
    }


    // black card will be automatically be picked
    // each time a judge is chosen

    //white cards will persist for players,
    // judge will not have access to cards during their turn

    //route needed for the submit of white card by players, not available for judge
//    @PostMapping("/submitWhiteCard")

    // and a route for the winning card submitted by judge, not available for players
//    @PostMapping("submitWinningCard")
//
//
//    public String leaderboardUpdate() {
//        //leaderboard will update with last winning combo
//        // winningCard is the whitecard selected by the judge
//        //leaderBoardString will equal currentBlackCard + winningCard
//        //if winningCard is true
//        //        leaderBoardString now equals white card and black card combo.
//        // return leaderBoardString;
//    }
//
//    //Status board will update with messages like, waiting for #of players
//    public String statusBoardUpdate() {
//        // If whitecards from at least one player is available,
//        // judge and players should see a message like
//        // "Waiting for {3} of {4} players"
//        //judge can pick a winner at any time to keep games going so
//        //the winning selection button is also a round ending button
//    }


}
