package io.codeagainsthumanity.models;

import javax.persistence.*;

@Entity
public class WhiteCard {
    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String text;

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

}
