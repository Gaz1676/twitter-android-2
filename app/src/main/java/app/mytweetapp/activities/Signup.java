package app.mytweetapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.mytweetapp.R;
import app.mytweetapp.helpers.ValidateHelper;

import static app.mytweetapp.helpers.MediaPlayerHelper.invalidInput;
import static app.mytweetapp.helpers.MediaPlayerHelper.validInput;
import static app.mytweetapp.helpers.ToastHelper.createToastMessage;

public class Signup extends AppCompatActivity {

    // called to do initial creation of the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signup_button = (Button) findViewById(R.id.signupButton);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup(view);
            }
        });
    }


    // details of new user created on sign up and saved in MyTweetApp Object
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
            startActivity(new Intent(this, Login.class));

            validInput(this);
            createToastMessage(this, "Welcome to MyTweetApp!");
        }
    }
}
