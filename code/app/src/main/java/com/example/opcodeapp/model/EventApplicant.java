package com.example.opcodeapp.model;

import android.companion.DeviceId;

import com.example.opcodeapp.enums.ApplicantStatus;
import com.google.firebase.firestore.DocumentId;

/**
 * Represents a mapping between a single event reference and user reference
 */
public class EventApplicant {

    @DocumentId
    private String id;

    private final String eventId;
    private final DeviceId userId;
    private ApplicantStatus status;

    /**
     * Overloaded constructor. The initial status is set to {@link ApplicantStatus#NOT_DRAWN}
     *
     * @param eventId The unique identifier of the event
     * @param userId  The unique device id associated with the user
     */
    public EventApplicant(String eventId, DeviceId userId) {
        this.eventId = eventId;
        this.userId = userId;
        this.status = ApplicantStatus.NOT_DRAWN;
    }

    /**
     * @return The unique id of the event
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * @return the device id associated with the user
     */
    public DeviceId getUserId() {
        return userId;
    }

    /**
     * @return the status of the applicant
     */
    public ApplicantStatus getStatus() {
        return status;
    }

    /**
     * Setter for the applicants status
     *
     * @param status the new applicant staus
     */
    public void updateStatus(ApplicantStatus status) {
        this.status = status;
    }
}
