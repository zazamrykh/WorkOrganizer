package com.example.workorganizer;

public enum WeekDays {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    @Override
    public String toString() {
        switch (this) {
            case MONDAY -> {
                return "Monday";
            }
            case TUESDAY -> {
                return "Tuesday";
            }
            case WEDNESDAY -> {
                return "Wednesday";
            }
            case THURSDAY -> {
                return "Thursday";
            }
            case FRIDAY -> {
                return "Friday";
            }
            case SATURDAY -> {
                return "Saturday";
            }
            case SUNDAY -> {
                return "Sunday";
            }
        }
        return null;
    }
}
