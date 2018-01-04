package app.mytweetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import app.mytweetapp.R;

import static app.mytweetapp.helpers.LogHelpers.info;
import static app.mytweetapp.helpers.MediaPlayerHelper.validInput;
import static app.mytweetapp.helpers.MediaPlayerHelper.welcome;

public class Welcome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Button login_button = (Button) findViewById(R.id.loginButton);
        final Button signup_button = (Button) findViewById(R.id.signupButton);

        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);
        welcome(this);
    }


    // onClick method activated when a button from page is clicked
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                info(this, "Login Pressed");
                startActivity(new Intent(this, Login.class));
                validInput(this);
                break;


            case R.id.signupButton:
                info(this, "Signup Pressed");
                startActivity(new Intent(this, Signup.class));
                validInput(this);
                break;
        }
    }
}
