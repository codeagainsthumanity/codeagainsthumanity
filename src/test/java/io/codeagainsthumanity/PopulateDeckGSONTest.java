package io.codeagainsthumanity;

import io.codeagainsthumanity.models.BlackCard;
import io.codeagainsthumanity.models.Game;
import io.codeagainsthumanity.models.WhiteCard;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PopulateDeckGSONTest {
    //instance
    PopulateDeckGSON p = new PopulateDeckGSON();
    Game game = new Game();

    @Before
    public void setUp() throws Exception {
        //arraylists of both decks
        ArrayList<WhiteCard> w = p.readWhiteFileGSON();
        ArrayList<BlackCard> b = p.readBlackFileGSON();
    }

    @Test
    public void gameTest() {
        game.getStatus();
    }

    @Test
    public void readBlackFileGSON() throws IOException {
        ArrayList<BlackCard> b = p.readBlackFileGSON();
        String actual = b.get(0).getText();
        String expected = "Why can't I sleep at night?";
    }

    @Test
    public void readWhiteFileGSON() throws IOException {
        ArrayList<WhiteCard> w = p.readWhiteFileGSON();
        String actual = w.get(0).getText();
        String expected = "Coat hanger abortions.";
    }

    @Test
    public void randomWhiteCard() throws IOException {
        ArrayList<WhiteCard> w = p.readWhiteFileGSON();
        WhiteCard check = p.randomWhiteCard(w);
        String text = check.getText();
        System.out.println(text);
        assertNotNull(text);
    }

    @Test
    public void randomBlackCard() throws IOException {
        ArrayList<BlackCard> b = p.readBlackFileGSON();
        BlackCard check = p.randomBlackCard(b);
        String text = check.getText();
        int pick = check.getPick();
        System.out.println("text:" + text);
        System.out.println("Pick:" + pick);
        assertTrue(pick == 0 || pick == 1 || pick == 2);
    }
}