package com.example.raf.boulderingsessionloger;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Login extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    boolean signUpMode = true;
    EditText password;
    EditText username;

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

            signUp(v);
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.backgroundLogin) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

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
                            Intent intent = new Intent(Login.this, Welcome.class);
                            startActivity(intent);
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
                            Intent intent = new Intent(Login.this, Welcome.class);
                            startActivity(intent);
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

        username = (EditText)findViewById(R.id.usernameEditText);
        password = (EditText)findViewById(R.id.passwordEditText);

        ConstraintLayout backgroundLogin = (ConstraintLayout)findViewById(R.id.backgroundLogin);

        backgroundLogin.setOnClickListener(this);

        password.setOnKeyListener(this);


        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }



}
