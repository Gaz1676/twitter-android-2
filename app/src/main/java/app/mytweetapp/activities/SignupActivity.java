package app.mytweetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.mytweetapp.R;
import app.mytweetapp.helpers.ValidateHelper;
import app.mytweetapp.main.MyTweetApp;
import app.mytweetapp.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.mytweetapp.helpers.MediaPlayerHelper.invalidInput;
import static app.mytweetapp.helpers.ToastHelper.createToastMessage;

public class SignupActivity extends AppCompatActivity implements Callback<User> {

    private MyTweetApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        app = (MyTweetApp) getApplication();

        Button signup_button = (Button) findViewById(R.id.signupButton);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup(view);
            }
        });
    }


    public void signup(View view) {
        TextView firstName = (TextView) findViewById(R.id.signupFirstName);
        TextView lastName = (TextView) findViewById(R.id.signupLastName);
        TextView email = (TextView) findViewById(R.id.signupEmail);
        TextView password = (TextView) findViewById(R.id.signupPassword);


        if (!ValidateHelper.isValidInput(firstName.getText().toString())) {
            invalidInput(this);
            firstName.setError("Please input first name");

        } else if (!ValidateHelper.isValidName(firstName.getText().toString())) {
            invalidInput(this);
            firstName.setError("Only use letters");

        } else if (!ValidateHelper.isValidInput(lastName.getText().toString())) {
            invalidInput(this);
            lastName.setError("Please input last name");

        } else if (!ValidateHelper.isValidName(lastName.getText().toString())) {
            invalidInput(this);
            lastName.setError("Only use letters");


        } else if (!ValidateHelper.isValidEmail(email.getText().toString())) {
            invalidInput(this);
            email.setError("Please enter a valid email");

        } else if (!ValidateHelper.isValidInput(password.getText().toString())) {
            invalidInput(this);
            password.setError("Please enter password");

        } else {
            User user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());

            MyTweetApp app = (MyTweetApp) getApplication();

            Call<User> call = app.tweetService.createUser(user);
            call.enqueue(this);
        }
    }


    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        User user = response.body();
        app.users.add(user);
        app.currentUser = user;
        startActivity(new Intent(this, TweetActivity.class));
        createToastMessage(this, "Fancy making a tweet " + user.firstName + " ?");
    }


    @Override
    public void onFailure(Call<User> call, Throwable t) {
        app.tweetServiceAvailable = false;
        createToastMessage(this, "You are a terrible programmer - go home!!!!");
        startActivity(new Intent(this, WelcomeActivity.class));
    }
}
