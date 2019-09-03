package io.codeagainsthumanity.models;

import javax.persistence.*;

@Entity
public class BlackCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String text;
    int pick;

    @ManyToOne
    Game gameInstance;

    @ManyToOne
    ApplicationUser player;

    @ManyToOne
    Deck deck;


    public BlackCard(String text, int pick) {
        this.text = text;
        this.pick = pick;
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Game getGameInstance() {
        return gameInstance;
    }


    public ApplicationUser getPlayer() {
        return player;
    }

    public void setPlayer(ApplicationUser player) {
        this.player = player;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPick() {
        return pick;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }
}
