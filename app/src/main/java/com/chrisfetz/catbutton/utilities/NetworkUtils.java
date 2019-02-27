package com.chrisfetz.catbutton.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    /*
     * String constants
     */
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String CATS_BASE_URL = "https://api.thecatapi.com/v1/images";
    private static final String SEARCH_PATH = "search";

    /*
     * Builds a URL that points to https://api.thecatapi.com/v1/images/search,
     * which contains a JSON object with a link to a cat picture.
     */
    public static URL buildSearchURL(){
        Uri builtUri = Uri.parse(CATS_BASE_URL).buildUpon()
                .appendPath(SEARCH_PATH)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);


        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     * This method is taken from the Google Android Udacity course
     * found here: https://classroom.udacity.com/courses/ud851
     * It returns the entire HTML content of a web page as a String.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}
