package com.chrisfetz.catbutton.utilities;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class JSONExtractor {

    /**
     * Extracts the url from a JSONObject
     * @param jsonObject A JSONObject built from raw html.
     * @return The string contained in the url field if it exists.
     */
    public static URL extractURL (JSONObject jsonObject){

        try {
            String urlString = jsonObject.getString("url");
            URL url = new URL(urlString);
            return url;
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        } catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Builds a JSONObject from a string.
     * @param rawHTML HTML pulled from NetworkUtils.getResponseFromHTTPUrl()
     * @return A JSONObject built from that response, if it's possible to make one.
     */
    public static JSONObject stringToJSON(String rawHTML){
        try {
            JSONArray jsonArray = new JSONArray(rawHTML);
            JSONObject jsonObject = new JSONObject(jsonArray.getString(0));
            return jsonObject;
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
