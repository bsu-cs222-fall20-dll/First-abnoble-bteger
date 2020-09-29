package edu.bsu.cs222;

public class Revision {
    private final String USER;
    private final String TIMESTAMP;

    public Revision(String user, String timeStamp) {
        this.USER = user;
        this.TIMESTAMP = timeStamp;
    }

    public String getUser() {
        return USER;
    }

    public String getTimeStamp() {
        return TIMESTAMP;
    }
}
