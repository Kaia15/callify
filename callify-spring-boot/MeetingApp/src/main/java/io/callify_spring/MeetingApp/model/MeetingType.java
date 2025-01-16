package io.callify_spring.MeetingApp.model;

public enum MeetingType {
    INSTANT(1), SCHEDULED(2), RECURRING_FIXED_TIME(4), RECURRING_NO_FIXED_TIME(3), 
    SCREEN_SHARE_ONLY(10);

    private final int value;
    MeetingType(int value) {
        this.value = value;
    }

    public int value() {return value;}

}
