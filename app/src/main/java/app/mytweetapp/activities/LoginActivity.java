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
import static app.mytweetapp.helpers.MediaPlayerHelper.validInput;
import static app.mytweetapp.helpers.ToastHelper.createToastMessage;


public class LoginActivity extends AppCompatActivity {

    boolean notValidated;

    /**
     * Login Activity Referenced from:
     * https://wit-ictskills-2017.github.io/mobile-app-dev/topic02-b/book-a-donation-03/index.html#/Donation-03
     */

    // called to do initial creation of the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_button = (Button) findViewById(R.id.loginButton);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });
    }


    // checks to see if the inputted data matches the user
    public void login(View view) {
        final MyTweetApp app = (MyTweetApp) getApplication();

        TextView email = (TextView) findViewById(R.id.loginEmail);
        TextView password = (TextView) findViewById(R.id.loginPassword);


        if (!ValidateHelper.isValidInput(email.getText().toString())) {
            invalidInput(this);
            email.setError("Please enter a valid email");
            notValidated = true;


        } else if (!ValidateHelper.isValidEmail(email.getText().toString())) {
            invalidInput(this);
            email.setError("Please enter a valid email");
            notValidated = true;


        } else if (!ValidateHelper.isValidInput(password.getText().toString())) {
            invalidInput(this);
            password.setError("Please enter password");
            notValidated = true;


        } else {

            User user = new User(null, null, email.getText().toString(), password.getText().toString());

            Call<User> call = app.tweetService.authenticate(user);
            call.enqueue(new Callback<User>() {


                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();

                    if (app.validUser(user.email, user.password)) {
                        validInput(getApplication());
                        startActivity (new Intent(LoginActivity.this, TimelineActivity.class));
                        createToastMessage(LoginActivity.this, "You are now logged in " + user.firstName);
                    } else {
                        invalidInput(getApplication());
                        createToastMessage(LoginActivity.this, "Wrong credentials - just wrong!");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    invalidInput(getApplication());
                    createToastMessage(LoginActivity.this, "Wrong credentials - seeeeeeeeeeriously wrong");
                }
            });
        }
    }
}