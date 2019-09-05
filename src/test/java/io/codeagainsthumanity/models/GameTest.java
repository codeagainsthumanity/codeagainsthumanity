package io.codeagainsthumanity.models;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    Game game = new Game(null, 0);

    public GameTest() throws IOException {
    }

    @Before
    public void setUp() throws Exception {
        //lists of both decks
        //List<WhiteCard> w = game.getWhiteDeck();
        //List<BlackCard> b = game.getBlackDeck();
    }

    @Test
    public void addCardToHand() {
        Long lon = Long.valueOf(3);
        game.addCardToHand(lon);
        game.addCardToHand(lon);
        game.addCardToHand(lon);
        game.addCardToHand(lon);
        game.addCardToHand(lon);
        game.addCardToHand(lon);
        game.addCardToHand(lon);

        List<String> wcl =  game.getHands().get(lon);

        System.out.println(wcl);

    }

    @Test
    public void removeCardFromHand() {
        Long lon = Long.valueOf(3);
        game.addCardToHand(lon);

        List<String> wcl =  game.getHands().get(lon);

        game.removeCardFromHand(lon, wcl.get(0));

        assertNull(wcl);
    }

    @Test
    public void updateBlackCardToBeJudgedAndPreviousBlack() {
        game.updateBlackCardToBeJudgedAndPreviousBlack();
        BlackCard bc = game.getCurrentBlack();
        System.out.println("CURRENT BLACK: " + bc.getText());
        BlackCard prevbc = game.getPreviousBlack();
        System.out.println("PREVIOUS BLACK: " + prevbc.getText());
    }

    @Test
    public void randomWhiteCard() {

        WhiteCard wc = game.randomWhiteCard();
        System.out.println("WHITECARD: " + wc.getText());
        assertNotNull(wc.getText());
    }

    @Test
    public void randomBlackCard() {
        BlackCard bc = game.randomBlackCard();
        System.out.println("BLACKCARD: " + bc.getText());
        assertNotNull(bc.getText());
    }
}