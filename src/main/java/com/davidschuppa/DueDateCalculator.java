package com.davidschuppa;

import java.time.LocalDateTime;

public class DueDateCalculator {

    private final int startHour = 9;
    private final int endHour = 17;

    public boolean isSubmitValid(LocalDateTime submitTime) {
        return submitTime.getHour() >= startHour && submitTime.getHour() <= endHour;
    }
}
