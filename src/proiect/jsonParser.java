package proiect;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.util.Iterator;

/**
 * @author Crunchify.com
 * How to Read JSON Object From File in Java?
 */

public class jsonParser {
    private  String pt;
   // private String pt;

    public jsonParser(String pt) {
        this.pt = pt;
    }

    @SuppressWarnings("unchecked")
    public UIBridge _parse() {
        UIBridge values=new UIBridge();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(this.pt));


            JSONObject jsonObject = (JSONObject) obj;


            JSONArray _weather= (JSONArray) jsonObject.get("weather");
            JSONObject _weather2= (JSONObject) _weather.get(0);

           values.weather= (String) _weather2.get("main");//vremea de baza
           values.weather_descr= (String) _weather2.get("description");//descrierea
           values.imgCode= (String) _weather2.get("icon");//vom folosi pentru imagini (tot de pe site-ul lor)
            //  http://openweathermap.org/img/wn/imgCode@FACTORMARIREx.png

           JSONObject _main= (JSONObject) jsonObject.get("main");
           values.temp= (Double) _main.get("temp");
           values.humidity= (Long) _main.get("humidity");

            JSONObject _wind= (JSONObject) jsonObject.get("wind");
            values.wind_speed= (Double) _wind.get("speed");
            return  values;

          //  }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
