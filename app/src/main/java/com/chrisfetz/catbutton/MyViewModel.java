package com.chrisfetz.catbutton;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

/**
 *
 * Code based on the StackOverflow answer here:
 * https://stackoverflow.com/questions/51601046/should-i-make-asynctask-member-of-livedata-or-repository-class-as-replacement
 *
 */
public class MyViewModel extends AndroidViewModel {
    @NonNull
    private MyRepository repo =  MyRepository.getInstance();

    @NonNull
    private LiveData<String> myLiveData;

    /**
     * Creates an instance of MyViewModel that gets its data from MyRepository.
     * @param application The CatButton application.
     */
    public MyViewModel(@NonNull Application application){
        super(application);

        myLiveData = repo.getMyLiveData();
    }

    @NonNull
    public LiveData<String> getMyLiveData(){
        return myLiveData;
    }
}
