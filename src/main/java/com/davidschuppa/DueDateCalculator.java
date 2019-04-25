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
     * The method checks if the given date is not on a weekend and only return true if its on a workday,
     * holidays are not considered.
     */
    public boolean isSubmitValid(LocalDateTime submission) {
        LocalTime submissionTime = submission.toLocalTime();
        DayOfWeek submissionDay = submission.toLocalDate().getDayOfWeek();
        boolean isValidTime = submissionTime.isAfter(startHour) && submissionTime.isBefore(endHour);
        boolean isNotWeekend = submissionDay != DayOfWeek.SATURDAY && submissionDay != DayOfWeek.SUNDAY;
        return isValidTime && isNotWeekend;
    }

    public LocalDateTime calculateDueDate(LocalDateTime submission, int turnAround) throws SubmissionTimeException, InvalidTurnAroundTimeException {
        if (!isSubmitValid(submission)) {
            throw new SubmissionTimeException("Problem only can be reported during working hours, and on workdays");
        } else if (turnAround < 0) {
            throw new InvalidTurnAroundTimeException("Turnaround time must be a positive integer");
        } else {
            if (submission.getHour() + turnAround <= endHour.getHour()) {
                LocalDateTime dueDate = submission.plusHours(turnAround);
                return dueDate;
            }
            return null;
        }
    }
}
