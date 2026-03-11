package com.example.opcodeapp.firestore.callback;

public interface FirestoreCallbackSend {
    void onSendSuccess(Void unused);
    void onSendFailure(Exception e);
}
