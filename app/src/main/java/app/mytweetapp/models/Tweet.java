package app.mytweetapp.models;

import app.mytweetapp.main.MyTweetApp;

/**
 * Created by gary on 05/01/18.
 */

public class Tweet {

    public String _id;
    public String text;
    public String date;
    public String tweeter;

    public Tweet(String message, String date) {
        this.text = message;
        this.date = date;
        tweeter = MyTweetApp.getCurrentUser();
    }

    public String getTweetId(){
        return _id;
    }
}
