package com.example.opcodeapp.firestore.callback;

import com.example.opcodeapp.model.Event;

import java.util.List;

public interface FirestoreCallbackEventsReceive {
    void onDataReceived(List<Event> items);
    void onError(Exception e);
}
