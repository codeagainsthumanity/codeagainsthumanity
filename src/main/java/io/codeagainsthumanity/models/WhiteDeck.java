package io.codeagainsthumanity.models;

import java.util.ArrayList;

public class WhiteDeck {
    //variables
    ArrayList<String> whiteCards;

    //constructor
    public WhiteDeck(ArrayList<String> whiteCards){
        this.whiteCards = whiteCards;
    }

    //sets and gets
    public ArrayList<String> getWhiteCards() {
        return whiteCards;
    }

    public void setWhiteCards(ArrayList<String> whiteCards) {
        this.whiteCards = whiteCards;
    }
}
