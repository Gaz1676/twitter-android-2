package app.mytweetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import app.mytweetapp.R;
import app.mytweetapp.main.MyTweetApp;
import app.mytweetapp.models.Tweet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.mytweetapp.helpers.ContactHelper.getContact;
import static app.mytweetapp.helpers.ContactHelper.getEmail;
import static app.mytweetapp.helpers.ContactHelper.sendEmail;
import static app.mytweetapp.helpers.IntentHelper.navigateUp;
import static app.mytweetapp.helpers.IntentHelper.selectContact;
import static app.mytweetapp.helpers.MediaPlayerHelper.invalidInput;
import static app.mytweetapp.helpers.ToastHelper.createToastMessage;


public class TweetActivity extends AppCompatActivity implements View.OnClickListener, Callback<Tweet> {

    private static final int REQUEST_CONTACT = 1;

    private EditText message;
    private TextView date;

    private Button tweetButton;
    private Button contactButton;
    private Button emailButton;
    //private Intent data;
    //private Tweet tweet;
    private String emailAddress = "";
    private MyTweetApp app;


    // called to do initial creation of the fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        app = (MyTweetApp) getApplication();

        message = (EditText) findViewById(R.id.message);
        date = (TextView) findViewById(R.id.tweetDate);
        tweetButton = (Button) findViewById(R.id.tweetButton);
        contactButton = (Button) findViewById(R.id.contactButton);
        emailButton = (Button) findViewById(R.id.emailButton);

       /* // http://www.technotalkative.com/android-get-current-date-and-time-in-different-format/
        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss aa");
        String currentDateTimeString = dateFormat.format(cal.getTime());
*/
        String currentDateTimeString = getFormatedDate();
        date.setText(Html.fromHtml(currentDateTimeString));

        date.setText(currentDateTimeString);

        contactButton.setOnClickListener(this);
        emailButton.setOnClickListener(this);
        date.setOnClickListener(this);
    }


    public void tweetPressed(View view) {
        Toast.makeText(app, "Creating tweet", Toast.LENGTH_SHORT).show();
        String text = message.getText().toString();


        if (text.length() > 0) {
            Tweet tweet = new Tweet(text, date.getText().toString());
            Call<Tweet> call = app.tweetService.createTweet(tweet);
            call.enqueue(this);
        } else {
            createToastMessage(this, "You forgot to enter your message!!");
            invalidInput(this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        getMenuInflater().inflate(R.menu.tweetpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.timeline:
                startActivity(new Intent(this, TimelineActivity.class));
                break;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.logout:
                startActivity(new Intent(this, WelcomeActivity.class));
                break;
            case android.R.id.home:
                navigateUp(this);
                return true;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contactButton:
                selectContact(this, REQUEST_CONTACT);
                break;

            case R.id.emailButton:
                sendEmail(this, emailAddress, getString(R.string.email_subject), message.getText().toString());
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CONTACT:
                String name = getContact(this, data);
                emailAddress = getEmail(this, data);
                contactButton.setText(name + " : " + emailAddress);
                break;
        }
    }


    @Override
    public void onResponse(Call<Tweet> call, Response<Tweet> response) {
        createToastMessage(this, "Tweet has been sent");
    }

    @Override
    public void onFailure(Call<Tweet> call, Throwable t) {
        createToastMessage(this, "Error creating Tweet");
    }


    private String getFormatedDate(){
        String dayNumberSuffix = getDayNumberSuffix(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        //SimpleDateFormat dateFormat = new SimpleDateFormat(" d'" + dayNumberSuffix + "' MMM yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d'" + dayNumberSuffix + "' yyyy, HH:mm:ss aa");

        return dateFormat.format(Calendar.getInstance().getTime());
    }

    private String getDayNumberSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "<sup>th</sup>";
        }
        switch (day % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
}
