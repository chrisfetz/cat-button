package com.chrisfetz.catbutton;

import com.chrisfetz.catbutton.utilities.NetworkUtils;

import org.junit.Test;

import static android.service.autofill.Validators.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(RobolectricTestRunner.class)
public class NetworkUtilsTest {
    private static final String catSearchString = "https://api.thecatapi.com/v1/images/search";

    @Test
    public void buildSearchURLShouldBuildCorrectURL(){
        try {
            URL url = NetworkUtils.buildSearchURL();
            URL testUrl = new URL(catSearchString);
            assertThat(url, is(equalTo(testUrl)));
        } catch (MalformedURLException e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void shouldReturnResultFromCatAPI(){
        try {
            URL testUrl = new URL(catSearchString);
            String response = NetworkUtils.getResponseFromHttpUrl(testUrl);
            assertNotNull(response);
        } catch (MalformedURLException e){
            e.printStackTrace();
            fail();
        } catch (IOException e){
            e.printStackTrace();
            fail();
        }
    }


}
