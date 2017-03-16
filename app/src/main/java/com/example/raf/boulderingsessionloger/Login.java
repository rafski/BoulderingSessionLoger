package com.example.raf.boulderingsessionloger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Login extends AppCompatActivity {

    boolean signUpMode = true;


    public void loginSwitch (View view){

        Button signUpButton = (Button)findViewById(R.id.signUpButton);

        Button logInButton = (Button)findViewById(R.id.logInButton);

        if(signUpMode){

            signUpMode = false;

            signUpButton.setText("Log In");
            logInButton.setText("Sign Up");

        }else{

            signUpMode = true;

            signUpButton.setText("Sign Up");
            logInButton.setText("Log In");

        }

    }

    public void signUp (View view){

        EditText username = (EditText)findViewById(R.id.usernameEditText);
        EditText password = (EditText)findViewById(R.id.passwordEditText);

        if (username.getText().toString().matches("")|| password.getText().toString().matches("")){

            Toast.makeText(Login.this,"Password and Username required",Toast.LENGTH_LONG).show();

        }else{

            if (signUpMode){

                ParseUser parseUser = new ParseUser();
                parseUser.setUsername(username.getText().toString());
                parseUser.setPassword(password.getText().toString());

                parseUser.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("Login", "OK");
                        } else {
                            Toast.makeText(Login.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }else{

                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null){

                            Log.i("Login", "OK");
                        } else {
                            Toast.makeText(Login.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }

                    }
                });


            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
}
