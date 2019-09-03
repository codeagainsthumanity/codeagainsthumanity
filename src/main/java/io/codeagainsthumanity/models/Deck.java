package io.codeagainsthumanity.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    int whiteCardCount;
    int blackCardCount;

    String[] cardsUsed;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deck")
    Set<WhiteCard> whiteCards;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deck")
    Set<BlackCard> blackCards;

    public Deck(int whiteCardCount, int blackCardCount, String[] cardsUsed) {
        this.whiteCardCount = whiteCardCount;
        this.blackCardCount = blackCardCount;
        this.cardsUsed = cardsUsed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWhiteCardCount() {
        return whiteCardCount;
    }

    public void setWhiteCardCount(int whiteCardCount) {
        this.whiteCardCount = whiteCardCount;
    }

    public int getBlackCardCount() {
        return blackCardCount;
    }

    public void setBlackCardCount(int blackCardCount) {
        this.blackCardCount = blackCardCount;
    }

    public String[] getCardsUsed() {
        return cardsUsed;
    }

    public void setCardsUsed(String[] cardsUsed) {
        this.cardsUsed = cardsUsed;
    }

    public Set<WhiteCard> getWhiteCards() {
        return whiteCards;
    }

    public void setWhiteCards(Set<WhiteCard> whiteCards) {
        this.whiteCards = whiteCards;
    }

    public Set<BlackCard> getBlackCards() {
        return blackCards;
    }

    public void setBlackCards(Set<BlackCard> blackCards) {
        this.blackCards = blackCards;
    }
}

