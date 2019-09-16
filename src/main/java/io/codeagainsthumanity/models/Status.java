package io.codeagainsthumanity.models;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Status {

    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    int statusCode;
    // status codes
    // 0: You're the judge and waiting for players to play their whitecard
    // 1: Player: Time to play a white card
    // 2: Waiting to be judged

    @ManyToOne
    ApplicationUser player;

    @ManyToOne
    Game game;

    //constructor the blank version
    public Status(){}
    //constructor
    public Status(int statusCode){
        this.statusCode = statusCode;
    }
    //methods
    public String getStatusMessage(int statusCode) {
        if (statusCode == 0) return "You're the judge and waiting for players to play their whitecard";
        if (statusCode == 1) return "Player: Time to play a white card";
        if (statusCode == 2) return "Waiting for Judgement";
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ApplicationUser getPlayer() {
        return player;
    }

    public void setPlayer(ApplicationUser player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
