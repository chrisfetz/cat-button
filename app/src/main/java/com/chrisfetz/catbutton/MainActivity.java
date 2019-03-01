package com.chrisfetz.catbutton;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.chrisfetz.catbutton.utilities.NetworkUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView myImageView;
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImageView = findViewById(R.id.iv_cat_picture);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        RequestManager requestManager = Glide.with(this);

        final Observer<String> urlObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String newUrl) {
                Log.v("LIVEDATA", "The livedata changed: " + newUrl);
                RequestBuilder requestBuilder =
                        requestManager.load(newUrl).fitCenter();
                requestBuilder.into(myImageView);
            }
        };

        myViewModel.getMyLiveData().observe(this, urlObserver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.button_zone, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.new_cat_button){
            MyRepository.getInstance().retrieveCatPictureUrl();
        }

        return super.onOptionsItemSelected(item);
    }
}
