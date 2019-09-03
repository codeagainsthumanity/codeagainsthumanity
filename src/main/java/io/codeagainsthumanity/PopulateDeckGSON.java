package io.codeagainsthumanity;

import com.google.gson.Gson;
import io.codeagainsthumanity.models.AllDecks;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PopulateDeckGSON {

    public static void readFileGson() throws IOException {
        try {

            FileReader file = new FileReader("./src/main/resources/static/cards.json");

            //File someFile = ResourceUtils.getFile("/src/main/resources/static/cards.json");
///Users/danikinn/Desktop/codefellows/401/codeagainsthumanity/src/main/resources/static/scripts/cards.json
            //static/scripts/cards.json
            String fileString = file.toString();

            Gson gson = new Gson();

            //TODO: make a new class and hold SPECIFIC elements required.
            AllDecks[] allCards = gson.fromJson(fileString, AllDecks[].class);

            System.out.println(allCards);
        }
        catch (FileNotFoundException e){
            System.out.println("File was not found.");
        }
        //return null;
    }
}
