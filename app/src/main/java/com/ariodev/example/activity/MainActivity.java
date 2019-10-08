package com.ariodev.example.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ariodev.example.R;
import com.ariodev.instagram.Instagram;
import com.ariodev.instagram.requests.payload.InstagramLoginResult;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

import static com.ariodev.instagram.InstagramConstants.getUserAgent;

public class MainActivity extends AppCompatActivity
{
    EditText etUsername;
    EditText etPassword;
    View view;
    private String TAG = "MainActivity";
    private Instagram instagram;


    @Override
    protected void onResume()
    {
        App.currentActivity = MainActivity.this;
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);


    }


    @SuppressLint("StaticFieldLeak")
    public void login(View view)
    {
        this.view = view;
        if (etUsername.toString()
                      .trim()
                      .length() > 0 || etPassword.toString()
                                                 .trim()
                                                 .length() > 0)
        {
            String username = etUsername.getText()
                                        .toString();
            String password = etPassword.getText()
                                        .toString();
            loginToInstagram(username, password);

        }

    }

    @SuppressLint("CheckResult")
    private void loginToInstagram(final String username, final String password)
    {
        instagram = Instagram.builder()
                             .username(username)
                             .password(password)
                             .activity(App.currentActivity)
                             .build();
        instagram.setup();
        loginAsyncTask.execute();

    }

    @SuppressLint("StaticFieldLeak")
    AsyncTask loginAsyncTask = new AsyncTask()
    {

        @Override
        protected Object doInBackground(Object[] objects)
        {
            Log.i(TAG, "doInBackground: ");
            try
            {

                InstagramLoginResult instagramLogin = instagram.login();
                if (instagramLogin.getStatus()
                                  .equals("ok"))
                {
                    Log.i(TAG, "loginFinished: " + getUserAgent());
                }
                else
                {

                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    };


}
