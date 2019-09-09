package io.codeagainsthumanity.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BlackDeck {
    //variables
    ArrayList <BlackCard> blackCards;


    //constructor
    public BlackDeck(ArrayList <BlackCard> blackCards){
        this.blackCards = blackCards;
    }

    //sets and gets

    public ArrayList<BlackCard> getBlackCards() {
        return blackCards;
    }

    public void setBlackCards(ArrayList<BlackCard> blackCards) {
        this.blackCards = blackCards;
    }
}
