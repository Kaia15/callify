package io.callify_spring.MeetingApp.model;

public enum RecurrenceType {
    NONE(0), DAILY(1), WEEKLY(2), MONTHLY(3);
    private final int value;

    RecurrenceType(int value) {
        this.value = value;
    }

    public int value() {return value;}
}
