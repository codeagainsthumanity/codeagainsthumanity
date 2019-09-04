package io.codeagainsthumanity.models;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    ArrayList<String> currentStatus;
    // ArrayList of status codes
    // 0: You're the judge and waiting for players to play their whitecard
    // 1: Player: Time to play a white card
    // 2: Waiting to be judged

    public Status(ArrayList<String> statusCodes) {
        statusCodes.add("You're the judge and waiting for players to play their whitecard");
        statusCodes.add("Player: Time to play a white card");
        statusCodes.add("Waiting for Judgement");

    }


    @OneToMany
    List<ApplicationUser>  player;

    @ManyToOne
    Game game;

}
