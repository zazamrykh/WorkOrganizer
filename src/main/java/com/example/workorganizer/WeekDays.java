package com.example.workorganizer;

public enum WeekDays {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public static WeekDays getWeekDay(int number) {
        WeekDays weekDay = null;
        switch (number){
            case 0 -> weekDay = MONDAY;
            case 1 -> weekDay = TUESDAY;
            case 2 -> weekDay = WEDNESDAY;
            case 3 -> weekDay = THURSDAY;
            case 4 -> weekDay = FRIDAY;
            case 5 -> weekDay = SATURDAY;
            case 6 -> weekDay = SUNDAY;
        }
        return weekDay;
    }

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

    public static WeekDays[] getValues(){
        WeekDays[] weekDays = new WeekDays[7];
        weekDays[0] = MONDAY;
        weekDays[1] = THURSDAY;
        weekDays[2] = WEDNESDAY;
        weekDays[3] = TUESDAY;
        weekDays[4] = FRIDAY;
        weekDays[5] = SATURDAY;
        weekDays[6] = SUNDAY;
        return weekDays;
    }
}
