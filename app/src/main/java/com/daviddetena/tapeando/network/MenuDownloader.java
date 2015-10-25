package com.daviddetena.tapeando.network;


import android.os.AsyncTask;

import com.daviddetena.tapeando.model.Allergen;
import com.daviddetena.tapeando.model.Course;
import com.daviddetena.tapeando.model.Menu;
import com.daviddetena.tapeando.utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

// AsyncTask para descargar en segundo plano el menu
public class MenuDownloader extends AsyncTask<String, Integer, Menu>{

    protected WeakReference<OnMenuReceivedListener> mOnMenuReceivedListener;

    public MenuDownloader(OnMenuReceivedListener onMenuReceivedListener){
        mOnMenuReceivedListener = new WeakReference<>(onMenuReceivedListener);
    }

    /**
     * Interface to get noticed when the download is complete
     */
    public interface OnMenuReceivedListener {
        void onMenuReceivedListener();
    }

    @Override
    protected Menu doInBackground(String... params) {
        Menu menu = Menu.getInstance();
        URL url;
        InputStream input = null;
        try {
            url = new URL(Constants.JSON_DROPBOX_URL);
            HttpURLConnection con = (HttpURLConnection) (url.openConnection());
            con.setConnectTimeout(5000);
            con.connect();
            int responseLength = con.getContentLength();
            byte data[] = new byte[1024];
            long currentBytes  = 0;
            int downloadedBytes;
            input = con.getInputStream();
            StringBuilder sb = new StringBuilder();
            while ((downloadedBytes = input.read(data)) != -1) {
                if (isCancelled()) {
                    input.close();
                    return null;
                }

                sb.append(new String(data, 0, downloadedBytes));

                // Si tuviéramos una longitud de respuesta podríamos incluso actualizar la barra de progreso
                if (responseLength > 0) {
                    currentBytes += downloadedBytes;
                    publishProgress((int) ((currentBytes * 100) / responseLength));
                }
                else {
                    currentBytes++;
                    publishProgress((int)currentBytes * 10);
                }
            }

            JSONObject jsonRoot = new JSONObject(sb.toString());
            JSONArray courseArray = jsonRoot.getJSONArray("courses");

            if (courseArray.length() > 0){

                menu.clearCourses();

                for (int i = 0; i < courseArray.length(); i++) {
                    JSONObject plate = courseArray.getJSONObject(i);

                    int identifier = plate.getInt("identifier");
                    String name = plate.getString("name");
                    String type = plate.getString("type");
                    String image = plate.getString("image");
                    String imageUrl = plate.getString("image_url");
                    String description = plate.getString("description");
                    float price = (float)plate.getDouble("price");

                    JSONArray allergensArray = plate.getJSONArray("allergens");
                    LinkedList<Allergen> allergens = new LinkedList<>();
                    for (int j = 0; j < allergensArray.length(); j++) {
                        JSONObject allergen = allergensArray.getJSONObject(j);
                        String aName = allergen.getString("name");
                        String aIcon = allergen.getString("icon");
                        allergens.add(new Allergen(aName, aIcon));
                    }
                    // Add the course to the menu
                    menu.addCourse(new Course(0, identifier, name, type, image, imageUrl, description, allergens, price));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return menu;
    }


    @Override
    protected void onPostExecute(Menu menu) {
        super.onPostExecute(menu);
        if (mOnMenuReceivedListener != null && mOnMenuReceivedListener.get() != null) {
            if (menu.getCourses().size()>0){
                mOnMenuReceivedListener.get().onMenuReceivedListener();
            }
        }
    }
}
