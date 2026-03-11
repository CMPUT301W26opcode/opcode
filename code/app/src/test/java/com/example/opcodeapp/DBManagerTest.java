package com.example.opcodeapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.opcodeapp.firestore.DBManager;
import com.example.opcodeapp.firestore.callback.FirestoreCallbackSend;
import com.example.opcodeapp.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DBManagerTest {
    private static final String EVENTS_COLLECTION = "events";
    private static final String USER_COLLECTION = "users";
    private static final String EVENT_USER_COLLECTION = "events_users";

    @Mock
    private FirebaseFirestore mockDb;
    @Mock
    private CollectionReference mockCollection;
    @Mock
    private DocumentReference mockDocRef;
    @Mock
    private Task<Void> mockTask;
    @Mock
    private FirestoreCallbackSend mockListener;

    private DBManager dbManager;

    @Before
    public void setup() {
        // 1. Initialize DBManager with the mocked database
        MockitoAnnotations.openMocks(this);

        // 2. Mock the chain
        when(mockDb.collection(USER_COLLECTION)).thenReturn(mockCollection);
        when(mockCollection.document()).thenReturn(mockDocRef);

        // 3. Mock the ID generation
        when(mockDocRef.getId()).thenReturn("mock_id_123");

        // 4. Mock the .set(user) call to return a task
        when(mockDocRef.set(any(User.class))).thenReturn(mockTask);

        dbManager = new DBManager(mockDb);
    }

    @Test
    public void testAddUser() {
        User user = User.builder("user_id_test")
                .name("Vedant Patel")
                .email("vspatel1@ualberta.ca")
                .phoneNumber("67676767")
                .build();

        // 5. Tell the mockTask to trigger the Success Listener immediately
        when(mockTask.addOnSuccessListener(any())).thenAnswer(invocation -> {
            OnSuccessListener<Void> listener = invocation.getArgument(0);
            listener.onSuccess(null); // Manually trigger the success callback
            return mockTask;
        });

        // Ensure the chain doesn't break if onFailure is called
        when(mockTask.addOnFailureListener(any())).thenReturn(mockTask);

        // EXECUTE
        dbManager.addUser(user, mockListener);

        // ASSERT & VERIFY
        assertEquals("mock_id_123", user.getId().getCustomId()); // Check if ID was set on user object
        verify(mockListener).onSendSuccess(null);      // Verify our callback was reached
        verify(mockListener, never()).onSendFailure(any());
    }

    @Test
    public void testUpdateUser() {
        User newUser = User.builder("user_id_john")
                .name("John Doe")
                .email("blah@gmail.com").phoneNumber("98372042")
                .build();
    }

}



