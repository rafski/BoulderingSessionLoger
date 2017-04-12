package com.example.raf.boulderingsessionloger;

/**
 * Created by raf on 17/03/2017.
 */

class Session {
    String numberOfSends;
    String averageSendDifficulty;
    String sessionID;
    String sessionDate;
    //location
    //total attempts

    Session(String numberOfSends, String averageSendDifficulty, String sessionDate, String sessionID) {
        this.numberOfSends = numberOfSends;
        this.averageSendDifficulty = averageSendDifficulty;
        this.sessionDate = sessionDate;
        this.sessionID = sessionID;

    }


}

