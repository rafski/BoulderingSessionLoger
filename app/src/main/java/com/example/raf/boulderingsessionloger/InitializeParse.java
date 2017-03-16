package com.example.raf.boulderingsessionloger;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

/**
 * Created by raf on 15/03/2017.
 */

public class InitializeParse extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("5c24335fa8b6a0935c03c223ed9f30d28b9eebcb")
                .clientKey("03aa2cf662d3dbaf7e9ddb4a49eb43d35f6f3cd4")
                .server("http://ec2-54-246-172-138.eu-west-1.compute.amazonaws.com:80/parse/")
                .build()
        );



        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
