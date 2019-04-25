package com.davidschuppa;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DueDateCalculatorTest {

    @Test
    public void smoke() {
        assertTrue(true);
    }

    @Test
    public void submitTimeIsNotValidIfEarly() {
        DueDateCalculator calculator = new DueDateCalculator();
        LocalDateTime submitTime = LocalDateTime.of(LocalDate.of(2019, 4, 25), LocalTime.of(8, 30));

        assertFalse(calculator.isSubmitValid(submitTime));
    }

    @Test
    public void submitTimeIsNotValidIfLate() {
        DueDateCalculator calculator = new DueDateCalculator();
        LocalDateTime submitTime = LocalDateTime.of(LocalDate.of(2019, 4, 25), LocalTime.of(17, 30));

        assertFalse(calculator.isSubmitValid(submitTime));
    }

    @Test
    public void submitTimeIsValidIfItsBetweenWorkingHours() {
        DueDateCalculator calculator = new DueDateCalculator();
        LocalDateTime submitTime = LocalDateTime.of(LocalDate.of(2019, 4, 25), LocalTime.of(13, 48));

        assertTrue(calculator.isSubmitValid(submitTime));
    }
}