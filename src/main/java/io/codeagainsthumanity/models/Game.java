package io.codeagainsthumanity.models;

import io.codeagainsthumanity.PopulateDeckGSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.IOException;
import java.sql.Time;
import java.util.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    boolean showRules;

    @OneToMany
    ArrayList<String> currentStatus;
    //user many statuses
    //game has many statuses
    // create status class
    // find by game and user in status repo

    double gameCode;
    BlackCard currentBlack;
    BlackCard previousBlack;
    WhiteCard previousWhite;

//    ArrayList<List<WhiteCard>> hands;
    List<WhiteCard> toBeJudged;

    ArrayList<WhiteCard> whiteDeck;
    ArrayList<BlackCard> blackDeck;

    @OneToMany
    List<WhiteCard> hand;

    @CreationTimestamp
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Date createdAt;

    @UpdateTimestamp
    @GeneratedValue(strategy = GenerationType.AUTO)
    Time updatedAt;

    @ManyToMany (mappedBy = "myGames")
    List<ApplicationUser> players;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gameInstance")
//    List<WhiteCard> currentWhiteDeck;
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gameInstance")
//    List<BlackCard> currentBlackDeck;

//    @OneToOne(mappedBy = "gameInstance")
//    BlackCard activeBlackCard;

    //TODO
    //Add previous black card and white card variables


    public Game(){};

    public Game(ApplicationUser gameOwner, double gameCode) throws IOException {
        this.showRules = true;
        this.whiteDeck = populateWhiteDeck();
        this.blackDeck = populateBlackDeck();
        this.gameCode = gameCode;
        this.players = createPlayerList(gameOwner);

    }

    public ArrayList<ApplicationUser> createPlayerList(ApplicationUser gameOwner){
        ArrayList<ApplicationUser> newPlayerList = new ArrayList<>();
        newPlayerList.add(gameOwner);
        return newPlayerList;
    }

    public ArrayList<WhiteCard> populateWhiteDeck() throws IOException {
        PopulateDeckGSON p = new PopulateDeckGSON();
        return p.readWhiteFileGSON();
    }

    public ArrayList<BlackCard> populateBlackDeck() throws IOException {
        PopulateDeckGSON p = new PopulateDeckGSON();
        return p.readBlackFileGSON();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isShowRules() {
        return showRules;
    }

    public void setShowRules(boolean showRules) {
        this.showRules = showRules;
    }


    public double getGameCode() {
        return gameCode;
    }

    public void setGameCode(double gameCode) {
        this.gameCode = gameCode;
    }

    public BlackCard getCurrentBlack() {
        return currentBlack;
    }

    public void setCurrentBlack(BlackCard currentBlack) {
        this.currentBlack = currentBlack;
    }

    public BlackCard getPreviousBlack() {
        return previousBlack;
    }

    public void setPreviousBlack(BlackCard previousBlack) {
        this.previousBlack = previousBlack;
    }

    public WhiteCard getPreviousWhite() {
        return previousWhite;
    }

    public void setPreviousWhite(WhiteCard previousWhite) {
        this.previousWhite = previousWhite;
    }

    public List<WhiteCard> getToBeJudged() {
        return toBeJudged;
    }

    public void setToBeJudged(List<WhiteCard> toBeJudged) {
        this.toBeJudged = toBeJudged;
    }

    public ArrayList<WhiteCard> getWhiteDeck() {
        return whiteDeck;
    }

    public void setWhiteDeck(ArrayList<WhiteCard> whiteDeck) {
        this.whiteDeck = whiteDeck;
    }

    public ArrayList<BlackCard> getBlackDeck() {
        return blackDeck;
    }

    public void setBlackDeck(ArrayList<BlackCard> blackDeck) {
        this.blackDeck = blackDeck;
    }

    public List<WhiteCard> getHand() {
        return hand;
    }

    public void setHand(List<WhiteCard> hand) {
        this.hand = hand;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Time getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Time updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ApplicationUser> getPlayers() {
        return players;
    }

    public void setPlayers(List<ApplicationUser> players) {
        this.players = players;
    }
}
