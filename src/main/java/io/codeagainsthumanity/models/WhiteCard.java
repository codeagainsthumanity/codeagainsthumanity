package io.codeagainsthumanity.models;

import javax.persistence.*;

@Entity
public class WhiteCard {
    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String text;

//    @ManyToOne
//    ApplicationUser player;
//
//    @ManyToOne
//    Game gameInstance;
//
//    @ManyToOne
//    Deck deck;

    //constructor
    public WhiteCard(){};
    public WhiteCard(String text) {
        this.text = text;
    }

    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public ApplicationUser getPlayer() {
//        return player;
//    }
//
//    public void setPlayer(ApplicationUser player) {
//        this.player = player;
//    }
//
//    public Game getGameInstance() {
//        return gameInstance;
//    }
//
//    public void setGameInstance(Game gameInstance) {
//        this.gameInstance = gameInstance;
//    }

}
