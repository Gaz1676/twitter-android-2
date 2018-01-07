package app.mytweetapp.main;


import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import app.mytweetapp.models.Tweet;
import app.mytweetapp.models.User;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyTweetApp extends Application {

    public TweetService tweetService;
    public boolean tweetServiceAvailable = false;
    public String service_url = "https://twitter-web.herokuapp.com/";

    public static User currentUser;
    public List<Tweet> tweets = new ArrayList<>();
    public List<User> users = new ArrayList<>();

    protected static MyTweetApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(service_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        tweetService = retrofit.create(TweetService.class);
        Log.v("Tweet", "MyTweetApp started");
    }


    public void newUser(User user) {
        users.add(user);
    }


    public void newTweet(Tweet tweet) {
        tweets.add(tweet);
    }


    // validates the user by their email & password
    // this lets the app know that it is a current user
    public boolean validUser(String email, String password) {
        for (User user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }


    public static String getCurrentUser() {
        return currentUser._id;
    }

    public static MyTweetApp getApp() {
        return app;
    }
}