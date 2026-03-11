package com.example.opcodeapp.firestore.callback;

import com.example.opcodeapp.model.User;

import java.util.List;

public interface FirestoreCallbackUsersReceive {
    void onDataReceived(List<User> items);
    void onError(Exception e);
}
