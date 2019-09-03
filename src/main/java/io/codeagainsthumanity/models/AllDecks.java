package io.codeagainsthumanity.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AllDecks {
    //variables
    ArrayList blackCards;
    ArrayList whiteCards;

    //constructor
    public AllDecks(ArrayList blackCards, ArrayList whiteCards){
        this.blackCards = blackCards;
        this.whiteCards = whiteCards;
    }

    //sets and gets
    public ArrayList getBlackCards() {
        return blackCards;
    }

    public void setBlackCards(ArrayList blackCards) {
        this.blackCards = blackCards;
    }

    public ArrayList getWhiteCards() {
        return whiteCards;
    }

    public void setWhiteCards(ArrayList whiteCards) {
        this.whiteCards = whiteCards;
    }
}
