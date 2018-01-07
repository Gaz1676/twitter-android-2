package app.mytweetapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.mytweetapp.R;
import app.mytweetapp.activities.SettingsActivity;
import app.mytweetapp.activities.TweetActivity;
import app.mytweetapp.activities.WelcomeActivity;
import app.mytweetapp.main.MyTweetApp;
import app.mytweetapp.models.Tweet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.mytweetapp.helpers.MediaPlayerHelper.validInput;
import static app.mytweetapp.helpers.ToastHelper.createToastMessage;

/**
 * Created by gary on 06/01/18.
 */


public class TimelineFragment extends ListFragment implements AdapterView.OnItemClickListener, AbsListView.MultiChoiceModeListener, Callback<List<Tweet>> {

    private TweetAdapter adapter;
    private ListView listView; // multi choice mode field

    private ArrayList<Tweet> tweets = new ArrayList<>();
    MyTweetApp app;


    // called to do initial creation of the fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.app_name);

        app = MyTweetApp.getApp();


        adapter = new TweetAdapter(getActivity(), tweets);
        setListAdapter(adapter);
    }


    // creates and returns the view hierarchy associated with the fragment.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        listView = v.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);
        return v;
    }


//    // called whenever a item gets clicked in the list fragment
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        Tweet tweet = ((TweetAdapter) getListAdapter()).getItem(position);
//        Intent i = new Intent(getActivity(), TweetActivity.class);
//        i.putExtra(TweetActivity.EXTRA_TWEET_ID, tweet.id);
//        startActivityForResult(i, 0);
//    }


    // method for the interface
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //   Tweet tweet = adapter.getItem(position);
        //   startActivityWithData(getActivity(), TweetActivity.class,"TWEET_ID", tweet.id);
    }


    // connects new menu to timeline Activity
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.timeline, menu);
    }


    // new instance created after selection from the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.menu_item_new_tweet:
//                Tweet tweet = new Tweet(app.currentUser);
//
//                timeline.addTweet(tweet);
//                validInput(getActivity());
//
//                Intent i = new Intent(getActivity(), TweetActivity.class);
//                //i.putExtra(TweetActivity.EXTRA_TWEET_ID, tweet.id);
//                startActivityForResult(i, 0);
//                return true;

            case R.id.menu_item_new_tweet:
                startActivity(new Intent(getActivity(), TweetActivity.class));
                validInput(getActivity());
                return true;


            case R.id.settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                validInput(getActivity());
                return true;


           /* case R.id.clear:
                for (int x = tweets.size() - 1; x >= 0; x--) {
                    timeline.deleteTweet(tweets.get(x));
                    adapter.notifyDataSetChanged();
                }
                validInput(getActivity());

                return true;
*/

            case R.id.logout:
                Intent in = new Intent(getActivity(), WelcomeActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(in, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // makes sure changes made in timeline Activity are shown in the list
    @Override
    public void onResume() {
        super.onResume();
        getTweets();
    }

    public void getTweets() {
        Call<List<Tweet>> call = app.tweetService.getAllTweets();
        call.enqueue(this);
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {}

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
        tweets.addAll(response.body());

        adapter.notifyDataSetChanged();
        app.tweetServiceAvailable = true;
    }

    @Override
    public void onFailure(Call<List<Tweet>> call, Throwable t) {
        createToastMessage(getActivity(), "Connection error, unable to retrieve tweets");
        app.tweetServiceAvailable = false;
    }


    // the timeline adapter updates the list with the tweet objects contained in the timeline
    class TweetAdapter extends ArrayAdapter<Tweet> {
        private Context context;
        List<Tweet> tweets;

        public TweetAdapter(Context context, ArrayList<Tweet> tweets) {
            super(context, 0, tweets);
            this.context = context;
            this.tweets = tweets;
        }


        // gets a View that displays the data at the specified position in the data set
        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tweet_row_list, null);
            }
            Tweet tweet = getItem(position);

            TextView message = convertView.findViewById(R.id.timeline_item_tweet);
            assert tweet != null;
            message.setText(tweet.text);

            TextView dateView = convertView.findViewById(R.id.timeline_item_dateTextView);
            dateView.setText(tweet.date);
            return convertView;
        }
    }
}
