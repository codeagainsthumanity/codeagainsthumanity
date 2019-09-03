package io.codeagainsthumanity.models;

import javax.persistence.*;

@Entity
public class WhiteCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String body;

    @ManyToOne
    ApplicationUser player;

    @ManyToOne
    Game gameInstance;

    @ManyToOne
    Deck deck;


    public WhiteCard(String body, ApplicationUser player, Game gameInstance) {
        this.body = body;
        this.player = player;
        this.gameInstance = gameInstance;
    }

    public WhiteCard(String body) {
        this.body = body;
    }

    // getters and setters
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

    public ApplicationUser getPlayer() {
        return player;
    }

    public void setPlayer(ApplicationUser player) {
        this.player = player;
    }

    public Game getGameInstance() {
        return gameInstance;
    }

    public void setGameInstance(Game gameInstance) {
        this.gameInstance = gameInstance;
    }

}
