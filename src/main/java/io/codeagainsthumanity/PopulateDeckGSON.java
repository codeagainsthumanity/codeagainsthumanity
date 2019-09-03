package io.codeagainsthumanity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.codeagainsthumanity.models.BlackCard;
import io.codeagainsthumanity.models.BlackDeck;
import io.codeagainsthumanity.models.WhiteDeck;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PopulateDeckGSON {

    public static void readBlackFileGSON() throws IOException {
        try {
//            Path path = Paths.get( "./src/main/resources/static/cards.json");
//            BufferedReader file = Files.newBufferedReader(path);
//
//            System.out.println(file);
//
//            Gson gson = new Gson();
//
//            //TODO: make a new class and hold SPECIFIC elements required.
//            BlackDeck[] allCards = gson.fromJson(file, BlackDeck[].class);
//
//            System.out.println(allCards);

            //start gson
            Gson gson = new Gson();
            //path
            String path = "./src/main/resources/static/blackCards.json";

            //scanner reads file
            String text = new String (Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

            //creates an artifact for gson casting
            TypeToken<List<BlackCard>> token = new TypeToken<List<BlackCard>>(){};
            //creates from text, list object
            BlackDeck blackDeck = gson.fromJson(text, BlackDeck.class);

            ArrayList<BlackCard> blackcards = blackDeck.getBlackCards();
            System.out.println(blackcards.get(0).getText());
            //System.out.println(blackDeck.getBlackCards()[0].getText());

        }
        catch (FileNotFoundException e){
            System.out.println("File was not found.");
        }
        //return null;
    }

    public static void readWhiteFileGSON() throws IOException {
        try {
            //start gson
            Gson gson = new Gson();
            //path
            String path = "./src/main/resources/static/whiteCards.json";

            //scanner reads file
            String text = new String (Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

            System.out.println(text);

            //creates an artifact for gson casting
            //TypeToken<List<BlackCard>> token = new TypeToken<List<BlackCard>>(){};
            //creates from text, list object
            WhiteDeck whiteDeck = gson.fromJson(text, WhiteDeck.class);

            //System.out.println(whiteCards);
            System.out.println(whiteDeck.getWhiteCards().get(0));

        }
        catch (FileNotFoundException e){
            System.out.println("File was not found.");
        }
        //return null;
    }
}
