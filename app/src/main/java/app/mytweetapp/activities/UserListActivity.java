package app.mytweetapp.activities;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import app.mytweetapp.R;
import app.mytweetapp.main.MyTweetApp;
import app.mytweetapp.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.mytweetapp.helpers.IntentHelper.navigateUp;
import static app.mytweetapp.helpers.ToastHelper.createToastMessage;


public class UserListActivity extends AppCompatActivity implements Callback<List<User>>, AdapterView.OnItemClickListener {

    private MyTweetApp app;
    private UserAdapter adapter;
    public List<User> users;
    private ListView listView;
    public TextView follow;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        app = (MyTweetApp) getApplication();

        listView = (ListView) findViewById(R.id.userlist);
        adapter = new UserAdapter(this, users);
        listView.setAdapter(adapter);


        Call<List<User>> call = app.tweetService.getAllUsers();
        call.enqueue(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.userlist_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                navigateUp(this);
                break;

            case R.id.tweet:
                startActivity(new Intent(this, TweetActivity.class));
                break;

            case R.id.timeline:
                startActivity(new Intent(this, TimelineActivity.class));
                break;

            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            case R.id.logout:
                startActivity(new Intent(this, WelcomeActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        adapter.users = response.body();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        createToastMessage(this, "Error retrieving Users");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    class UserAdapter extends ArrayAdapter<User> {

        private MyTweetApp app;
        private Context context;
        public List<User> users = new ArrayList<>();

        public UserAdapter(Context context, List<User> users) {
            super(context, R.layout.user_row_list, users);
            this.context = context;
            this.users = users;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.user_row_list, parent, false);
            User user = users.get(position);

            TextView userFullName = view.findViewById(R.id.userFullName);
            userFullName.setText(user.firstName + " " + user.lastName);

            TextView button = view.findViewById(R.id.followButton);
            if (MyTweetApp.currentUser.following != null) {
                if (MyTweetApp.currentUser.following.contains(user._id)) {
                    button.setText("unfollow");
                } else {
                    button.setText("follow");
                }
            }
            return view;
        }
    }
}
