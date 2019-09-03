import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PopulateDeckDB {

    public void readFilePopDB() throws IOException {
        try {
            //File bmp = new File("/scripts/cards.json");

            // parsing file "JSONExample.json"
            Object obj = new JSONParser().parse(new FileReader("scripts/cards.json"));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // getting firstName and lastName
            //String firstName = (String) jo.get("firstName");

            // getting phoneNumbers
            JSONArray ja = (JSONArray) jo.get("blackCards");
            System.out.println(ja);

        }
        catch (FileNotFoundException | JSONException e){
            System.out.println("File was not found OR JSON exception.");
        }
        //return null;
    }
}
