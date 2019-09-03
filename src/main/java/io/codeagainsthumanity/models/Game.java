package io.codeagainsthumanity.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    boolean showRules;
    String statusOfGame;
    double gameCode;

    @CreationTimestamp
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Date createdAt;

    @UpdateTimestamp
    @GeneratedValue(strategy = GenerationType.AUTO)
    Time updatedAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gameInstance")
    List<ApplicationUser> players;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gameInstance")
    List<WhiteCard> currentWhiteDeck;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gameInstance")
    List<BlackCard> currentBlackDeck;

    @OneToOne(mappedBy = "gameInstance")
    BlackCard activeBlackCard;

    public Game(ApplicationUser gameOwner, double gameCode) {
        this.showRules = true;
        this.statusOfGame = "New Game";
        this.gameCode = gameCode;
        this.players.set(0, gameOwner);
    }


    public double getRoomCode() {
        return gameCode;
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

    public List<ApplicationUser> getPlayer() {
        return players;
    }

    public void setPlayer(ArrayList<ApplicationUser> players) {
        this.players = players;
    }

    public List<WhiteCard> getCurrentWhiteCard() {
        return currentWhiteDeck;
    }

    public void setCurrentWhiteCard(ArrayList<WhiteCard> currentWhiteCard) {
        this.currentWhiteDeck = currentWhiteCard;
    }

    public List<BlackCard> getCurrentBlackCard() {
        return currentBlackDeck;
    }

    public void setCurrentBlackCard(ArrayList<BlackCard> currentBlackCard) {
        this.currentBlackDeck = currentBlackCard;
    }

    public Game(Boolean showRules) {
        this.showRules = showRules;
    }

    public String getStatusOfGame() {
        return statusOfGame;
    }

    public void setStatusOfGame(String statusOfGame) {
        this.statusOfGame = statusOfGame;
    }

    public List<WhiteCard> getCurrentWhiteDeck() {
        return currentWhiteDeck;
    }

    public void setCurrentWhiteDeck(ArrayList<WhiteCard> currentWhiteDeck) {
        this.currentWhiteDeck = currentWhiteDeck;
    }

    public List<BlackCard> getCurrentBlackDeck() {
        return currentBlackDeck;
    }

    public void setCurrentBlackDeck(ArrayList<BlackCard> currentBlackDeck) {
        this.currentBlackDeck = currentBlackDeck;
    }

    public BlackCard getActiveBlackCard() {
        return activeBlackCard;
    }

    public void setActiveBlackCard(BlackCard activeBlackCard) {
        this.activeBlackCard = activeBlackCard;
    }
}
