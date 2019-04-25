package com.davidschuppa;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DueDateCalculator {

    private LocalTime startHour;
    private LocalTime endHour;

    public DueDateCalculator(LocalTime startHour, LocalTime endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    /**
     * The method validates the submission time, using LocalTime compareTo method.
     * The comparator value is negative if less and positive if greater than the time its compared to.
     * The method checks if the given date is not on a weekend and only return true if its on a workday,
     * holidays are not considered.
     */
    public boolean isSubmitValid(LocalDateTime submission) {
        LocalTime submissionTime = submission.toLocalTime();
        DayOfWeek submissionDay = submission.toLocalDate().getDayOfWeek();
        boolean isValidTime = submissionTime.compareTo(startHour) > 0 && submissionTime.compareTo(endHour) < 0;
        boolean isNotWeekend = submissionDay != DayOfWeek.SATURDAY && submissionDay != DayOfWeek.SUNDAY;
        return isValidTime && isNotWeekend;
    }
}
