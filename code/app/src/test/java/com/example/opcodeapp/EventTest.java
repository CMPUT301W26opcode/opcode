package com.example.opcodeapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.opcodeapp.model.Event;
import com.example.opcodeapp.model.User;

import java.time.LocalDateTime;

public class EventTest {

    private Event mockEvent() {
        User organizer = MockUtil.mockUser(0);
        
    }


    //tests for addApplicant and getApplicants
    @Test
    void testAddApplicant() {
        User applicant = new User("mock_applicant_1", "mock_applicant_1@ualberta.ca", "686868686868");
        Event event = mockEvent();
        event.addApplicant(applicant);
        assertTrue(event.getApplicants().contains(applicant));

    }

    //tests for setInvited and getInvited
    @Test
    void testSetInvited() {
        User applicant = new User("mock_applicant_2", "mock_applicant_2@ualberta.ca", "69696969696969");
        Event event = mockEvent();
        event.addApplicant(applicant);
        event.setInvited(event.getApplicants());
        assertTrue(event.getInvited().contains(applicant));


    }

    //tests for setAttendee and getAttendees
    @Test
    void testSetAttendee() {
        User applicant = new User("mock_applicant_3", "mock_applicant_3@ualberta.ca", "707070707070");
        Event event = mockEvent();
        event.addApplicant(applicant);
        event.setAttendee(applicant);
        assertTrue(event.getAttendees().contains(applicant));

    }

    //tests for setDeclined and getDeclined
    @Test
    void testSetDeclined() {
        User applicant = new User("mock_applicant_4", "mock_applicant_4@ualberta.ca", "717171717171");
        Event event = mockEvent();
        event.addApplicant(applicant);
        event.setDeclined(applicant);
        assertTrue(event.getDeclined().contains(applicant));

    }



}
