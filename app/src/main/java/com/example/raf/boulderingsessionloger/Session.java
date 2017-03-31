package com.example.raf.boulderingsessionloger;

/**
 * Created by raf on 17/03/2017.
 */

class Session {
    String numberOfSends;
    String averageSendDifficulty;
    String sessionID;
    //location
    //total attempts

    Session(String numberOfSends, String averageSendDifficulty, String sessionID) {
        this.numberOfSends = numberOfSends;
        this.averageSendDifficulty = averageSendDifficulty;
        this.sessionID = sessionID;
    }


}

