package com.davidschuppa;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DueDateCalculatorTest {

    private DueDateCalculator calculator = new DueDateCalculator(LocalTime.of(9, 0), LocalTime.of(17, 0));

    @Test
    public void smoke() {
        assertTrue(true);
    }

    @Test
    public void submitTimeIsNotValidIfEarly() {
        LocalDateTime submitTime = LocalDateTime.of(LocalDate.of(2019, 4, 25), LocalTime.of(8, 30));

        assertFalse(calculator.isSubmitValid(submitTime));
    }

    @Test
    public void submitTimeIsNotValidIfLate() {
        LocalDateTime submitTime = LocalDateTime.of(LocalDate.of(2019, 4, 25), LocalTime.of(17, 30));

        assertFalse(calculator.isSubmitValid(submitTime));
    }

    @Test
    public void submitTimeIsValidIfItsBetweenWorkingHours() {
        LocalDateTime submitTime = LocalDateTime.of(LocalDate.of(2019, 4, 25), LocalTime.of(13, 48));

        assertTrue(calculator.isSubmitValid(submitTime));
    }

    @Test
    public void submitTimeIsNotValidOnWeekends() {
        LocalDateTime submitTime = LocalDateTime.of(LocalDate.of(2019, 4, 20), LocalTime.of(13, 48));

        assertFalse(calculator.isSubmitValid(submitTime));
    }

    @Test
    public void calculateDueDateThrowsExceptionIfSubmissionTimeNotValid() {
        assertThrows(SubmissionTimeException.class, () -> calculator.calculateDueDate(LocalDateTime.of(
                LocalDate.of(2019, 04, 20),
                LocalTime.of(9, 30)), 16));
    }

    @Test
    public void calculateDueDateThrowsExceptionIfTurnAroundTimeIsNotValid() {
        assertThrows(InvalidTurnAroundTimeException.class, () -> calculator.calculateDueDate(LocalDateTime.of(
                LocalDate.of(2019, 04, 25),
                LocalTime.of(9, 30)), -3));
    }

    @Test
    public void calculateDueDateReturnsDueDateIfTurnAroundFitsWorkHours() throws SubmissionTimeException, InvalidTurnAroundTimeException {
        LocalDate date = LocalDate.of(2019,4,25);
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(13, 48));
        assertEquals(dateTime.plusHours(2L), calculator.calculateDueDate(dateTime, 2));
    }

    @Test
    public void calculateDueDateReturnsNextDayIfTurnAroundIsMoreThanWorkHours() throws SubmissionTimeException, InvalidTurnAroundTimeException {
        LocalDate date = LocalDate.of(2019,4,25);
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(13, 0));
        LocalDateTime expected = LocalDateTime.of(date.plusDays(1), LocalTime.of(13, 0));

        assertEquals(expected, calculator.calculateDueDate(dateTime, 8));
    }

    @Test
    public void calculateDueDateAddsPlusDaysAndRemainingHoursIfTurnAroundNotMultipleOfWorkHours() throws SubmissionTimeException, InvalidTurnAroundTimeException {
        LocalDate date = LocalDate.of(2019,4,25);
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(13, 0));
        LocalDateTime expected = LocalDateTime.of(date.plusDays(3), LocalTime.of(10, 0));

        assertEquals(expected, calculator.calculateDueDate(dateTime, 21));
    }
}