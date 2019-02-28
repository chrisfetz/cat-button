package com.chrisfetz.catbutton;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.chrisfetz.catbutton.utilities.JSONExtractor;
import com.chrisfetz.catbutton.utilities.NetworkUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

/**
 * The repo from which MyViewModel pulls its data, thus ensuring separation of concerns
 *
 * Code based on the StackOverflow answer here:
 * https://stackoverflow.com/questions/51601046/should-i-make-asynctask-member-of-livedata-or-repository-class-as-replacement
 *
 */
public class MyRepository {

    private static MyRepository instance;

    @NonNull
    private MutableLiveData<String> myLiveData = new MutableLiveData<>();

    /**
     * Creates an instance of MyRepository if one doesn't exist already.
     * @return An instance of MyRepository
     */
    public static MyRepository getInstance() {
        if (instance == null){
            synchronized (MyRepository.class) {
                if (instance == null) {
                    instance = new MyRepository();
                }
            }
        }
        return instance;
    }


    @NonNull
    public MutableLiveData<String> getMyLiveData() {
        return myLiveData;
    }

    /**
     * Creates a background thread that visits https://api.thecatapi.com/v1/images/search,
     * reads the JSON from there, extracts the URL from it and returns it as a String.
     * It posts this string to myLiveData, assuming an error has not occurred.
     */
    public void retrieveCatPictureUrl(){
        new Thread (() -> {
            String postedValue = null;
            try {
                URL catJSONURL = NetworkUtils.buildSearchURL();
                String response = NetworkUtils.getResponseFromHttpUrl(catJSONURL);
                JSONObject jsonObject = JSONExtractor.stringToJSON(response);
                URL imageURL = JSONExtractor.extractURL(jsonObject);
                postedValue = imageURL.toString();
            } catch (IOException e){
                e.printStackTrace();
            }
            myLiveData.postValue(postedValue);
        }).start();

    }
}
