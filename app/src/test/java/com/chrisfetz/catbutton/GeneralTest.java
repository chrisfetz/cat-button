package com.chrisfetz.catbutton;

import com.chrisfetz.catbutton.utilities.JSONExtractor;
import com.chrisfetz.catbutton.utilities.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(RobolectricTestRunner.class)
public class GeneralTest {
    @Test
    public void shouldExtractJSONFromSearchURL(){
        try {
            URL url = NetworkUtils.buildSearchURL();
            String response = NetworkUtils.getResponseFromHttpUrl(url);
            JSONObject builtObject = JSONExtractor.stringToJSON(response);
            URL responseURL = JSONExtractor.extractURL(builtObject);

            assertNotNull(responseURL);
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
