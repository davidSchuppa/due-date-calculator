package com.davidschuppa;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DueDateCalculator {

    private final LocalTime startHour = LocalTime.of(9,0);
    private final LocalTime endHour = LocalTime.of(17,0);

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
