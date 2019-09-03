import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PopulateDeckDB {

    public void readFilePopDB() throws IOException {
        try {
            //File bmp = new File("/scripts/cards.json");
            //grab our file from path, read as a stream.
            //BufferedImage image = ImageIO.read(bmp);
            
            // parsing file "JSONExample.json"
            Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;
            //return;

            // getting firstName and lastName
            String firstName = (String) jo.get("firstName");
            String lastName = (String) jo.get("lastName");

            System.out.println(firstName);
            System.out.println(lastName);
        }
        catch (FileNotFoundException | JSONException e){
            System.out.println("File was not found OR JSON exception.");
        }
        //return null;
    }
}
