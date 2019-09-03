package io.codeagainsthumanity.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Time;
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
    String roomCode;

    @CreationTimestamp
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Date createdAt;

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
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

    @UpdateTimestamp
    @GeneratedValue(strategy = GenerationType.AUTO)
    Time updatedAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gameInstance")
    Set<ApplicationUser> player;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gameInstance")
    Set<WhiteCard> currentWhiteDeck;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gameInstance")
    Set<BlackCard> currentBlackDeck;

    @OneToOne(mappedBy = "gameInstance")
    BlackCard activeBlackCard;

    public Game(boolean showRules, String statusOfGame, String roomCode) {
        this.showRules = showRules;
        this.statusOfGame = statusOfGame;
        this.roomCode = roomCode;
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

    public Set<ApplicationUser> getPlayer() {
        return player;
    }

    public void setPlayer(Set<ApplicationUser> player) {
        this.player = player;
    }

    public Set<WhiteCard> getCurrentWhiteCard() {
        return currentWhiteDeck;
    }

    public void setCurrentWhiteCard(Set<WhiteCard> currentWhiteCard) {
        this.currentWhiteDeck = currentWhiteCard;
    }

    public Set<BlackCard> getCurrentBlackCard() {
        return currentBlackDeck;
    }

    public void setCurrentBlackCard(Set<BlackCard> currentBlackCard) {
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

    public Set<WhiteCard> getCurrentWhiteDeck() {
        return currentWhiteDeck;
    }

    public void setCurrentWhiteDeck(Set<WhiteCard> currentWhiteDeck) {
        this.currentWhiteDeck = currentWhiteDeck;
    }

    public Set<BlackCard> getCurrentBlackDeck() {
        return currentBlackDeck;
    }

    public void setCurrentBlackDeck(Set<BlackCard> currentBlackDeck) {
        this.currentBlackDeck = currentBlackDeck;
    }

    public BlackCard getActiveBlackCard() {
        return activeBlackCard;
    }

    public void setActiveBlackCard(BlackCard activeBlackCard) {
        this.activeBlackCard = activeBlackCard;
    }
}
