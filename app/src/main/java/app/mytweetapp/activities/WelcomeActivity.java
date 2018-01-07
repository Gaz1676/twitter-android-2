package app.mytweetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

import app.mytweetapp.R;
import app.mytweetapp.main.MyTweetApp;
import app.mytweetapp.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.mytweetapp.helpers.MediaPlayerHelper.validInput;
import static app.mytweetapp.helpers.MediaPlayerHelper.welcome;
import static app.mytweetapp.helpers.ToastHelper.createToastMessage;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, Callback<List<User>> {

    private MyTweetApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Button login_button = (Button) findViewById(R.id.loginButton);
        final Button signup_button = (Button) findViewById(R.id.signupButton);

        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);

        app = (MyTweetApp) getApplication();
        welcome(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        app.currentUser = null;

        Call<List<User>> call = app.tweetService.getAllUsers();
        call.enqueue(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                if (app.tweetServiceAvailable) {
                    startActivity(new Intent(this, LoginActivity.class));
                    validInput(this);
                } else {
                    serviceUnavailableMessage();
                }
                break;


            case R.id.signupButton:
                if (app.tweetServiceAvailable) {
                    startActivity(new Intent(this, SignupActivity.class));
                    validInput(this);
                } else {
                    serviceUnavailableMessage();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {

        serviceAvailableMessage();
        app.users = response.body();
        app.tweetServiceAvailable = true;
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        app.tweetServiceAvailable = false;
        serviceUnavailableMessage();
    }

    void serviceUnavailableMessage() {
        createToastMessage(this, "Awww nooooo - No connection");

    }

    void serviceAvailableMessage() {
        createToastMessage(this, "Connected - you are not a loser!");
    }
}
