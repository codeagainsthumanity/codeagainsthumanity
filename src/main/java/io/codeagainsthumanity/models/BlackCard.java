package io.codeagainsthumanity.models;

import javax.persistence.*;

@Entity
public class BlackCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String text;
    int pick;

    public BlackCard(String text, int pick) {
        this.text = text;
        this.pick = pick;
    }
    public BlackCard(){}

    //getters and setters
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

    public int getPick() {
        return pick;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }
}
