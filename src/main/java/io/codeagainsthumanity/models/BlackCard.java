package io.codeagainsthumanity.models;

import javax.persistence.*;

@Entity
public class BlackCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String body;

    @ManyToOne
    Game gameInstance;

    @ManyToOne
    ApplicationUser player;

    @ManyToOne
    Deck deck;


    public BlackCard(String body, Game gameInstance) {
        this.body = body;
        this.gameInstance = gameInstance;
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Game getGameInstance() {
        return gameInstance;
    }

    public void setGameInstance(Game gameInstance) {
        this.gameInstance = gameInstance;
    }

    public ApplicationUser getPlayer() {
        return player;
    }

    public void setPlayer(ApplicationUser player) {
        this.player = player;
    }

}
