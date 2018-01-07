/*
package app.mytweetapp.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.mytweetapp.R;
import app.mytweetapp.main.MyTweetApp;
import app.mytweetapp.models.Tweet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.mytweetapp.helpers.MediaPlayerHelper.validInput;
import static app.mytweetapp.helpers.ToastHelper.createToastMessage;


public class TimelineActivity extends AppCompatActivity implements Callback<List<Tweet>> {

    private MyTweetApp app;
    private TweetAdapter adapter;
    private ListView listView;
    private final Context context = this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        app = (MyTweetApp) getApplication();

        listView = (ListView) findViewById(R.id.timeline);
        adapter = new TweetAdapter(this, app.tweets);
        listView.setAdapter(adapter);

        Call<List<Tweet>> call = app.tweetService.getAllTweets();
        call.enqueue(this);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to remove this tweet?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        Toast.makeText(getApplicationContext(), "Unable to remove tweet!!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    // connects new menu to timeline Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }


    // new instance created after selection from the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.timeline:
                startActivity(new Intent(this, TimelineActivity.class));
                createToastMessage(this, "Refreshed Global Timeline");
                validInput(this);
                break;

            case R.id.userlist:
                startActivity(new Intent(this, UserListActivity.class));
                createToastMessage(this, "Welcome to your UserList");
                validInput(this);
                break;

            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                validInput(this);
                return true;


            case R.id.logout:
                Intent in = new Intent(this, WelcomeActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(in, 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
        adapter.tweets = response.body();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<Tweet>> call, Throwable t) {
        createToastMessage(this, "Error retrieving tweets");
    }


    class TweetAdapter extends ArrayAdapter<Tweet> {
        private Context context;
        public List<Tweet> tweets = new ArrayList<>();

        public TweetAdapter(Context context, List<Tweet> tweets) {
            super(context, R.layout.tweet_row_list, tweets);
            this.context = context;
            this.tweets = tweets;
        }

        // gets a View that displays the data at the specified position in the data set
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.tweet_row_list,parent, false);
            Tweet tweet = tweets.get(position);

            TextView message = view.findViewById(R.id.timeline_item_tweet);
            message.setText(tweet.message);

            TextView dateView = view.findViewById(R.id.timeline_item_dateTextView);
            dateView.setText(tweet.date);
            return view;
        }

        @Override
        public int getCount() {
            //return tweets.size();
            return 0;
        }
    }


}
*/

/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.mytweetapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import app.mytweetapp.R;
import app.mytweetapp.fragments.TimelineFragment;

public class TimelineActivity extends AppCompatActivity {


    /**
     * Timeline Activity Referenced from:
     * https://wit-ictskills-2017.github.io/mobile-app-dev/topic05-b/book-a-myrent-07%20(Fragments)/index.html#/04
     */

    // called to do initial creation of the fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);


        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new TimelineFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }
}
