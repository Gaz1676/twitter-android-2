package app.mytweetapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.mytweetapp.R;
import app.mytweetapp.main.MyTweetApp;
import app.mytweetapp.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.mytweetapp.helpers.ToastHelper.createToastMessage;


public class SettingsActivity extends AppCompatActivity implements Callback<User> {

    MyTweetApp app;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        app = (MyTweetApp) getApplication();

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        firstName.setText(app.currentUser.firstName);
        lastName.setText(app.currentUser.lastName);
        email.setText(app.currentUser.email);

        Button update_button = (Button) findViewById(R.id.updateButton);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(view);
            }
        });
    }


    public void update(View view) {

        User user = app.currentUser;
        user.firstName = firstName.getText().toString();
        user.lastName = lastName.getText().toString();
        user.email = email.getText().toString();
        user.password = password.getText().toString();

        Call<User> call = app.tweetService.updateUser(user);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        createToastMessage(this, "Updated " + app.currentUser.firstName + "'s settings");
        this.finish();
    }


    @Override
    public void onFailure(Call<User> call, Throwable t) {
        app.tweetServiceAvailable = false;
        createToastMessage(this, "Nope - Service is down");
        startActivity(new Intent(this, WelcomeActivity.class));
    }
}