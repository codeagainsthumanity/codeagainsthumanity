package io.codeagainsthumanity;

import com.google.gson.Gson;
import io.codeagainsthumanity.models.BlackCard;
import io.codeagainsthumanity.models.BlackDeck;
import io.codeagainsthumanity.models.WhiteCard;
import io.codeagainsthumanity.models.WhiteDeck;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class PopulateDeckGSON {

    public ArrayList<BlackCard> readBlackFileGSON() throws IOException {
        try {
            //start gson
            Gson gson = new Gson();
            //path
            //String path = "./src/main/resources/static/blackCards.json";
            //scanner reads file
            //String text = new String (Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            //creates from text, list object

            InputStream in;
            in = getClass().getResourceAsStream("/static/blackCards.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuilder text = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                text.append(line + "\n");
            }

            BlackDeck blackDeck = gson.fromJson(String.valueOf(text), BlackDeck.class);
            //unwrap the deck into cards.
            ArrayList<BlackCard> blackCards = blackDeck.getBlackCards();

            //System.out.println(blackCards.get(0).getText());

            return blackCards;

        }
        catch (FileNotFoundException e){
            System.out.println("File was not found.");
        }
        return null;
    }

    public ArrayList<WhiteCard> readWhiteFileGSON() throws IOException {
        try {
            //start gson
            Gson gson = new Gson();
            //path
            //String path = "./src/main/resources/static/whiteCards.json";
            //scanner reads file
            //String text = new String (Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

            InputStream in;
            in = getClass().getResourceAsStream("/static/whiteCards.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuilder text = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                text.append(line + "\n");
                //System.out.print(text);
            }

            // Input from json
            WhiteDeck whiteDeck = gson.fromJson(String.valueOf(text), WhiteDeck.class);
            //unwrap the deck into cards.
            ArrayList<String> whiteCards = whiteDeck.getWhiteCards();
            //output structure
            ArrayList<WhiteCard> output = new ArrayList<>();
            //massage strings into whitecards
            for(String whitetext : whiteCards){
                output.add(new WhiteCard(whitetext));
            }

            //System.out.println(whiteDeck.getWhiteCards().get(0));

            return output;
        }
        catch (FileNotFoundException e){
            System.out.println("File was not found.");
        }
        return null;
    }

    public WhiteCard randomWhiteCard(ArrayList<WhiteCard> w){
        int index = getRandomNumberInRange(0, w.size()-1);

        return w.get(index);
    }

    public BlackCard randomBlackCard(ArrayList<BlackCard> b){
        int index = getRandomNumberInRange(0, b.size()-1);

        return b.get(index);
    }

    //sauce: https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
