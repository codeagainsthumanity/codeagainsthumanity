import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PopulateDeckGSON {

    public void readFilePopDB() throws IOException {
        try {

            FileReader file = new FileReader("scripts/cards.json");

            String fileString = file.toString();

            Gson gson = new Gson();

            //TODO: make a new class and hold SPECIFIC elements required.
            ArrayList allCards = gson.fromJson(fileString, ArrayList.class);

            System.out.println(allCards);
        }
        catch (FileNotFoundException e){
            System.out.println("File was not found OR JSON exception.");
        }
        //return null;
    }
}
