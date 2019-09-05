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
    public RedirectView createGame(Principal p) throws IOException {
        ApplicationUser gameOwner = applicationUserRepository.findByUsername(p.getName());
        double gameCode = Math.random() * 100;
        Game newGame = new Game(gameOwner, gameCode);
        Status status = new Status();
        //use method to add the method to the users list of games
        gameOwner.addToMyGames(newGame);
        status.setStatusCode(0); //status code for first player is set to 0
//        status.set
        status.setGame(newGame);
        status.setPlayer(gameOwner);
        System.out.println("STATUS CODE: " + status.getStatusCode());

        //save databases
        gameRepository.save(newGame);
        applicationUserRepository.save(gameOwner);
        statusRepository.save(status);

        return new RedirectView("/game/" + gameCode);
    }

    @GetMapping ("/game/{gameCode}")
    public String getGame(Principal p, Model m, @PathVariable double gameCode){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());

        m.addAttribute("currentGame", gameRepository.findByGameCode(gameCode));

        m.addAttribute("principalUser", p);
        return "gameroom";
    }


    //TODO:
    // joinGame Post route(pass in current user)
    // Use setter of game instance into its player's lists
    // save game instance in repo

    // black card will be automatically be picked
    // each time a judge is chosen

    //white cards will persist for players,
    // judge will not have access to white cards during their turn

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
