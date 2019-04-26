package com.davidschuppa;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * DueDateCalculator helps to calculate the correct due dates for
 * problem reports based on the turnaround time given in working hours.
 * Working hours can be specified in the constructor with a starting and an
 * ending hour.
 */
public class DueDateCalculator {

    private LocalTime startHour;
    private LocalTime endHour;

    public DueDateCalculator(LocalTime startHour, LocalTime endHour) {
        if (endHour.isBefore(startHour)) {
            throw new IllegalArgumentException("Start hour must be before end hour!");
        }
        this.startHour = startHour;
        this.endHour = endHour;
    }

    /**
     * The method validates the submission time, using LocalTime compareTo method.
     * The method checks if the given date is not on a weekend and only return true if its on a workday,
     * holidays are not considered.
     */
    boolean isSubmitValid(LocalDateTime submission) {
        LocalTime submissionTime = submission.toLocalTime();
        DayOfWeek submissionDay = submission.getDayOfWeek();
        boolean isValidTime = submissionTime.isAfter(startHour) && submissionTime.isBefore(endHour);
        boolean isNotWeekend = submissionDay != DayOfWeek.SATURDAY && submissionDay != DayOfWeek.SUNDAY;
        return isValidTime && isNotWeekend;
    }

    /**
     * The method calculates a due date for reported problems from the given arguments
     *
     * @param submission LocalDateTime of the submission date
     * @param turnaround turnaround time, given in working hours
     * @return LocalDateTime of the correct due date
     * @throws SubmissionTimeException        thrown when a report is submitted after or before working hours
     * @throws InvalidTurnAroundTimeException thrown when turnaround value is invalid
     */
    LocalDateTime calculateDueDate(LocalDateTime submission, int turnaround) throws SubmissionTimeException, InvalidTurnAroundTimeException {
        if (!isSubmitValid(submission)) {
            throw new SubmissionTimeException("Problem can only be reported during working hours, and on workdays");
        } else if (turnaround < 0) {
            throw new InvalidTurnAroundTimeException("Turnaround time must be a positive integer");
        } else {
            LocalDateTime dueDate = submission;
            if (submission.getHour() + turnaround <= endHour.getHour()) {
                return dueDate.plusHours(turnaround);
            } else {
                int days = countFullDays(turnaround);
                int plusHours = countRemainingHours(turnaround);

                if (dueDate.plusHours(plusHours).getHour() > endHour.getHour()) {
                    days++;
                    plusHours = plusHours - (endHour.getHour() - submission.getHour());
                    dueDate = dueDate.withHour(9);
                }
                dueDate = setDueDateToNextWorkDay(dueDate, days);
                return dueDate.plusHours(plusHours);
            }
        }
    }

    /**
     * Method sets the due date recursively, skipping weekends
     *
     * @param date starting date to count due date from
     * @param days number of days to add to starting date
     * @return LocalDateTime due date set to the correct date
     */
    private LocalDateTime setDueDateToNextWorkDay(LocalDateTime date, int days) {
        if ((date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
            return setDueDateToNextWorkDay(date.plusDays(1), days);
        } else {
            if (days == 0)
                return date;
            days--;
            return setDueDateToNextWorkDay(date.plusDays(1), days);
        }
    }

    /**
     * Helping method to count how many full working days
     * the turnaround hours give
     *
     * @param turnAround turnaround time in working hours
     * @return integer of days
     */
    private int countFullDays(int turnAround) {
        int workHours = endHour.getHour() - startHour.getHour();
        return turnAround / workHours;
    }

    /**
     * Helping method to count the remaining hours above the
     * full days given by turnaround time
     *
     * @param turnAround turnaround time in working hours
     * @return integer of remaining hours
     */
    private int countRemainingHours(int turnAround) {
        int workHours = endHour.getHour() - startHour.getHour();
        return turnAround - (turnAround / workHours) * workHours;
    }
}
