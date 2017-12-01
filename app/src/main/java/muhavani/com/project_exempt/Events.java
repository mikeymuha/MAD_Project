package muhavani.com.project_exempt;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by mikey on 05/10/2017.
 */

public class Events {
    public String title;
    public String place;
    public String imageUrl;
    public String time;
    public String date;
    public String url;

    public static ArrayList<Events> getEventsFromFile(String filename, Context context){
        final ArrayList<Events> EventList = new ArrayList<>();

        try {
            // Load data
            String jsonString = loadJsonFromAsset("events.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray EVents = json.getJSONArray("Events");

            // Get events objects from data
            for(int i = 0; i < EVents.length(); i++){

               JSONObject obj = EVents.getJSONObject(i);
                Events events = new Events();

                events.title = EVents.getJSONObject(i).getString("Event");
                events.place = EVents.getJSONObject(i).getString("Place");
                events.imageUrl = EVents.getJSONObject(i).getString("Image");

                events.time = EVents.getJSONObject(i).getString("Time");
                events.date = EVents.getJSONObject(i).getString("Date");
                events.url = EVents.getJSONObject(i).getString("url");

                EventList.add(events);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return EventList;
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}


