package com.example.haykuproductions.maestrodetalle.dummy;

import android.os.AsyncTask;
import android.util.Log;

import com.example.haykuproductions.maestrodetalle.HttpHandler;
import com.example.haykuproductions.maestrodetalle.ItemListActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        new GetContacts().execute();
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    private static class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall("http://iesayala.ddns.net/oilstealer1/json01.php");

            Log.e("Control", "JSON");
            if (jsonStr != null) {
                Log.e("Control", "JSON NOT NULL");
                try {
                    //JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    // JSONArray contacts = jsonObj.getJSONArray("contacts");
                    JSONArray guitars = jsonObj.getJSONArray("SERIES");

                    // looping through All Contacts
                    for (int i = 0; i < guitars.length(); i++) {
                        Log.e("Control", "ENTRA FOR OBTENER GUITARRAS");
                        JSONObject c = guitars.getJSONObject(i);

                        String nombre = c.getString("Nombre");
                        String descripcion = c.getString("Descripcion");
                        String genero = c.getString("genero");

                        addItem(new DummyItem(genero, nombre, descripcion));
                    }
                } catch (final JSONException e) {
                    Log.e(ItemListActivity.class.getSimpleName(), "Json parsing error: " + e.getMessage());
                }
            }

            return null;
        }

    }

    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
