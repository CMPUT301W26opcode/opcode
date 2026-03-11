package com.example.opcodeapp.firestore;

import com.example.opcodeapp.firestore.callback.FirestoreCallbackEventsReceive;
import com.example.opcodeapp.firestore.callback.FirestoreCallbackSend;
import com.example.opcodeapp.firestore.callback.FirestoreCallbackUsersReceive;
import com.example.opcodeapp.model.Event;
import com.example.opcodeapp.model.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.util.List;
import java.util.function.Consumer;

public class DBManager {

    // Constants
    private static final String EVENTS_COLLECTION = "events";
    private static final String USER_COLLECTION = "users";
    private static final String EVENT_USER_COLLECTION = "events_users";


    /**
     * FirebaseFirestore instance.
     */
    private final FirebaseFirestore db;

    /**
     * CollectionReference for the Users collection.
     */
    private CollectionReference usersRef;

    /**
     * CollectionReference for the Events collection.
     */
    private CollectionReference eventsRef;

    /**
     * CollectionReference for the EventApplicant
     */
    private CollectionReference eventUsersRef;

    /**
     * Constructor for DBManager. Initializes the FirebaseFirestore instance.
     */
    public DBManager(FirebaseFirestore DB) {
        this.db = DB;
        usersRef = db.collection(USER_COLLECTION);
        eventsRef = db.collection(EVENTS_COLLECTION);
        eventUsersRef = db.collection(EVENT_USER_COLLECTION);
    }

    //Many other methods may be needed for events/users such as deleting, searching for events/users based on attributes.

    /**
     * Generates a new id for an event
     *
     * @return the String id
     */
    public String getNewEventId() {
        DocumentReference newDocRef = db.collection(EVENTS_COLLECTION).document();
        return newDocRef.getId();
    }

    /**
     * Adds an event to the Events collection in Firestore.
     *
     * @param event    The event to be added.
     * @param listener The listener to be notified of the success or failure of the operation.
     */
    public void addEvent(Event event, FirestoreCallbackSend listener) {
        DocumentReference newDocRef = db.collection(EVENTS_COLLECTION).document(event.getId());
        newDocRef.set(event)
                .addOnSuccessListener(listener::onSendSuccess)
                .addOnFailureListener(listener::onSendFailure);
    }


    /**
     * Updates an event in the Events collection in Firestore.
     *
     * @param event    The updated event.
     * @param listener The listener to be notified of the success or failure of the operation.
     */
    public void updateEvent(Event event, FirestoreCallbackSend listener) {


        db.collection(EVENTS_COLLECTION).document(event.getId())
                .set(event, SetOptions.merge())
                .addOnSuccessListener(listener::onSendSuccess)
                .addOnFailureListener(listener::onSendFailure);
    }

    /**
     * Fetches events from the Events collection in Firestore and notifies the listener.
     *
     * @param filter   The filter options to be applied to the query
     * @param listener The listener to be notified of the success or failure of the operation.
     *
     */
    public void fetchEvents(Consumer<Query> filter, FirestoreCallbackEventsReceive listener) {
        CollectionReference ref = db.collection(EVENTS_COLLECTION);
        filter.accept(ref);
        ref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Event> items = task.getResult().toObjects(Event.class);
                listener.onDataReceived(items); // Send data back to Activity
            } else {
                listener.onError(task.getException());
            }
        });
    }


    /**
     * Adds a user to the Users collection in Firestore.
     *
     * @param user     The user to be added.
     * @param listener The listener to be notified of the success or failure of the operation.
     */
    public void addUser(User user, FirestoreCallbackSend listener) {


        DocumentReference newDocRef = db.collection(USER_COLLECTION).document();
        newDocRef.set(user)
                .addOnSuccessListener(listener::onSendSuccess)
                .addOnFailureListener(listener::onSendFailure);
    }


    /**
     * Updates a user in the Users collection in Firestore.
     *
     * @param user     The updated user.
     * @param listener The listener to be notified of the success or failure of the operation.
     */
    public void updateUser(User user, FirestoreCallbackSend listener) {
        String docId = user.getId().getCustomId();
        if (docId == null || docId.isEmpty()) {
            listener.onSendFailure(new Exception("User ID is missing. Update aborted."));
            return;
        }
        db.collection(USER_COLLECTION).document(docId)
                .set(user, SetOptions.merge())
                .addOnSuccessListener(listener::onSendSuccess)
                .addOnFailureListener(listener::onSendFailure);
    }

    /**
     * Fetches users from the Users collection in Firestore and notifies the listener.
     *
     * @param listener The listener to be notified of the success or failure of the operation.
     */
    public void fetchUsers(Consumer<Query> filter, FirestoreCallbackUsersReceive listener) {
        CollectionReference ref = db.collection(USER_COLLECTION);
        filter.accept(ref);

        ref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<User> items = task.getResult().toObjects(User.class);
                listener.onDataReceived(items); // Send data back to Activity
            } else {
                listener.onError(task.getException());
            }
        });

    }
}
