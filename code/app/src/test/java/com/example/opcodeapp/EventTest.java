package com.example.opcodeapp;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import android.content.Context;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventTest {

    @Mock
    private FirebaseFirestore mockDb;
    @Mock private CollectionReference mockCollection;
    @Mock private DocumentReference mockDocRef;
    @Mock private Task<Void> mockTask;
    @Mock private FirestoreCallbackSend mockListener;

    @Mock private Context mockContext;


    private DBManager dbManager;

    @Before
    public void setup() {
        // 1. Initialize DBManager with the mocked database

        MockitoAnnotations.openMocks(this);


        // 2. Mock the chain: db.collection("Users").document()
        when(mockDb.collection("Users")).thenReturn(mockCollection);
        when(mockCollection.document()).thenReturn(mockDocRef);

        // 3. Mock the ID generation
        when(mockDocRef.getId()).thenReturn("mock_id_123");

        // 4. Mock the .set(user) call to return a task
        when(mockDocRef.set(any(User.class))).thenReturn(mockTask);

        when(mockContext.getContentResolver()).thenReturn(mock(android.content.ContentResolver.class));


        dbManager = new DBManager(mockDb);
    }

    private Event mockEvent() {
        User organizer = new User("mock_organizer", "mock_organizer@ualberta.ca", "676767676", mockContext);

        Event(String name, String location, String description, LocalDateTime start, LocalDateTime registrationStart, LocalDateTime end, LocalDateTime registrationEnd, User organizer, float price, int waitlistLimit)
        return new Event("Oscars", "California", "Film festival and presentation of awards", LocalDateTime.of(2026, 3, 15, 0, 0), LocalDateTime.now(), LocalDateTime.of(2026, 3, 15, 0, 0), LocalDateTime.of(2026, 3, 14, 16, 0), organizer, 0, 50);

    }


    //tests for addApplicant and getApplicants
    @Test
    void testAddApplicant() {
        User applicant = new User("mock_applicant_1", "mock_applicant_1@ualberta.ca", "686868686868", mockContext);
        Event event = mockEvent();
        event.addApplicant(applicant);
        assertTrue(event.getApplicants().contains(applicant));

    }

    //tests for setInvited and getInvited
    @Test
    void testSetInvited() {
        User applicant = new User("mock_applicant_2", "mock_applicant_2@ualberta.ca", "69696969696969", mockContext);
        Event event = mockEvent();
        event.addApplicant(applicant);
        event.setInvited(event.getApplicants());
        assertTrue(event.getInvited().contains(applicant));


    }

    //tests for setAttendee and getAttendees
    @Test
    void testSetAttendee() {
        User applicant = new User("mock_applicant_3", "mock_applicant_3@ualberta.ca", "707070707070", mockContext);
        Event event = mockEvent();
        event.addApplicant(applicant);
        event.setAttendee(applicant);
        assertTrue(event.getAttendees().contains(applicant));

    }

    //tests for setDeclined and getDeclined
    @Test
    void testSetDeclined() {
        User applicant = new User("mock_applicant_4", "mock_applicant_4@ualberta.ca", "717171717171", mockContext);
        Event event = mockEvent();
        event.addApplicant(applicant);
        event.setDeclined(applicant);
        assertTrue(event.getDeclined().contains(applicant));

    }



}
