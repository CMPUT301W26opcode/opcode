package com.example.opcodeapp.model;

import android.companion.DeviceId;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.opcodeapp.util.ParcelableUtil;
import com.google.firebase.firestore.DocumentId;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event implements Parcelable {

    public static final Creator<Event> CREATOR = new Creator<>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @DocumentId
    @NonNull
    private final String id;
    private String name;
    private String location;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime registrationStart;
    private LocalDateTime registrationEnd;
    private DeviceId organizerId;

    /**
     * Overloaded constructor for the Event class. The id is randomly generated.
     *
     * @param name                  The name of the event.
     * @param location              The location of the event.
     * @param description           The description of the event.
     * @param start             The start date of the event.
     * @param registrationStart The registration start time of the event.
     * @param end               The end date of the event.
     * @param registrationEnd   The registration end time of the event.
     * @param organizerId           The organizer of the event.
     */
    private Event(@NonNull String id, String name, String location, String description,
                  LocalDateTime registrationStart, LocalDateTime registrationEnd,
                  LocalDateTime start, LocalDateTime end, DeviceId organizerId) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
        this.start = start;
        this.end = end;
        this.organizerId = organizerId;
    }

    /**
     * @param in Instance of the Parcel
     */
    protected Event(Parcel in) {
        this.id = Objects.requireNonNull(in.readString());
        this.name = in.readString();
        this.location = in.readString();
        this.description = in.readString();
        this.start = ParcelableUtil.readLocalDateTime(in);
        this.end = ParcelableUtil.readLocalDateTime(in);
        this.registrationEnd = ParcelableUtil.readLocalDateTime(in);
        this.registrationStart = ParcelableUtil.readLocalDateTime(in);
        this.organizerId = ParcelableUtil.readDeviceId(in);
    }

    /**
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(description);
        dest.writeSerializable(start);
        dest.writeSerializable(end);
        dest.writeSerializable(registrationEnd);
        dest.writeSerializable(registrationStart);
        dest.writeParcelable(organizerId, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Getter for the ID of the event.
     *
     * @return The ID of the event.
     */
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Getter for the name of the event.
     *
     * @return The name of the event.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the event.
     *
     * @param name The name of the event.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the description of the event.
     *
     * @return The description of the event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the event.
     *
     * @param description The description of the event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the location of the event.
     *
     * @return The location of the event.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for the location of the event.
     *
     * @param location The location of the event.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter for the start date of the event.
     *
     * @return The start date of the event.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Setter for the start date of the event.
     *
     * @param start The start date of the event.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Getter for the end date of the event.
     *
     * @return The end date of the event.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Setter for the end date of the event.
     *
     * @param end The end date of the event.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Getter for the registration end time of the event.
     *
     * @return The registration end time of the event.
     */
    public LocalDateTime getRegistrationEnd() {
        return registrationEnd;
    }

    /**
     * Setter for the registration end time of the event.
     *
     * @param registrationEnd The registration end time of the event.
     */
    public void setRegistrationEnd(LocalDateTime registrationEnd) {
        this.registrationEnd = registrationEnd;
    }

    /**
     * Getter for the registration start time of the event.
     *
     * @return The registration start time of the event.
     */
    public LocalDateTime getRegistrationStart() {
        return registrationStart;
    }

    /**
     * Setter for the registration start time of the event.
     *
     * @param registrationStart The registration start time of the event.
     */
    public void setRegistrationStart(LocalDateTime registrationStart) {
        this.registrationStart = registrationStart;
    }


    /**
     * Getter for the organizer of the event.
     *
     * @return The organizer of the event.
     */
    public DeviceId getOrganizerId() {
        return organizerId;
    }

    /**
     * Setter for the organizer of the event.
     *
     * @param organizerId The organizer of the event.
     */
    public void setOrganizerId(DeviceId organizerId) {
        this.organizerId = organizerId;
    }

    public static Builder builder(String id) {
        return builder(id);
    }

    /**
     * Builder class for Events
     * TODO: Add input validation
     */
    public static class Builder {
        private final String id;
        private String name;
        private String location;
        private String description;
        private LocalDateTime start;
        private LocalDateTime end;
        private LocalDateTime registrationStart;
        private LocalDateTime registrationEnd;
        private DeviceId organizerId;

        public Builder(String id) {
            this.id = id;
        }

        public Builder name(@NonNull String name) {
            this.name = name;
            return this;
        }

        public Builder location(@NonNull String location) {
            this.location = location;
            return this;
        }

        public Builder description(@NonNull String description) {
            this.description = description;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.start = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.end = endDate;
            return this;
        }

        public Builder registrationStartTime(LocalDateTime registrationStartTime) {
            this.registrationStart = registrationStartTime;
            return this;
        }

        public Builder registrationEndTime(LocalDateTime registrationEndTime) {
            this.registrationEnd = registrationEndTime;
            return this;
        }

        public Builder organizerId(DeviceId organizerId) {
            this.organizerId = organizerId;
            return this;
        }

        public Event build() {
            LocalDateTime now = LocalDateTime.now();

            // Check if registration does not start in the past
            if (registrationStart.isBefore(now))
                throw new IllegalArgumentException("Registration start time cannot be in the past");

            // Check if registration end is after the start
            if (!registrationEnd.isAfter(registrationStart))
                throw new IllegalArgumentException("Registration end date must be after registration start");

            // Check if the event start is in the future
            if (!start.isAfter(now))
                throw new IllegalArgumentException("Event start must be in the future");

            // Check if the event end is after the start
            if (!end.isAfter(start))
                throw new IllegalArgumentException("Event end must be after event start");

            return new Event(id, name, location, description, registrationStart, registrationEnd, start, end, organizerId);
        }
    }
}
