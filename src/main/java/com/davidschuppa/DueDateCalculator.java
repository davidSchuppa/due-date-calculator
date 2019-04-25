package com.davidschuppa;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DueDateCalculator {

    private final LocalTime startHour = LocalTime.of(9,0);
    private final LocalTime endHour = LocalTime.of(17,0);

    /**
     *The method validates the submission time, using LocalTime compareTo method.
     *The comparator value is negative if less and positive if greater than the time its compared to.
     */
    public boolean isSubmitValid(LocalDateTime submitTime) {
        LocalTime submission = submitTime.toLocalTime();

        return submission.compareTo(startHour) > 0 && submission.compareTo(endHour) < 0;
    }
}
