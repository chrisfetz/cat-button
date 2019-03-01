package com.chrisfetz.catbutton.utilitytests;

import com.chrisfetz.catbutton.utilities.JSONExtractor;

import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.net.MalformedURLException;
import java.net.URL;


@RunWith(RobolectricTestRunner.class)
public class JSONExtractorTest {
    static String JSONwithURL = "[{\"breeds\":[],\"id\":\"2om\",\"url\":\"https://cdn2.thecatapi.com/images/2om.gif\"}]";
    static String JSONwithoutURL = "[{\"breeds\":[],\"id\":\"2om\"}]";
    static String notJSON = "Swordfish";


    @Test
    public void shouldConvertValidJSONStringToJSON(){
        JSONObject jsonObject = JSONExtractor.stringToJSON(JSONwithURL);
        assertNotNull(jsonObject);
    }

    @Test
    public void shouldReturnNullForInvalidJSON(){
        JSONObject jsonObject = JSONExtractor.stringToJSON(notJSON);
        assertNull(jsonObject);
    }

    @Test
    public void shouldExtractURLFromValidJSON(){
        JSONObject jsonObject = JSONExtractor.stringToJSON(JSONwithURL);
        try {
            URL url = JSONExtractor.extractURL(jsonObject);
            URL desiredURL = new URL("https://cdn2.thecatapi.com/images/2om.gif");
            assertThat(url, is(equalTo(desiredURL)));
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnNullIfNoURL(){
        JSONObject jsonObject = JSONExtractor.stringToJSON(JSONwithoutURL);
        URL url = JSONExtractor.extractURL(jsonObject);

        assertThat(url, is(equalTo(null)));
    }

    @Test
    public void shouldReturnNullIfNotJSON(){
        JSONObject jsonObject = JSONExtractor.stringToJSON(notJSON);
        URL url = JSONExtractor.extractURL(jsonObject);

        assertThat(url, is(equalTo(null)));
    }
}
