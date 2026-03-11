package com.example.opcodeapp.model;

import android.companion.DeviceId;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.opcodeapp.util.ParcelableUtil;
import com.google.firebase.firestore.DocumentId;

import java.util.Objects;

public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @DocumentId
    @NonNull
    private final DeviceId id;
    private String name;
    private String email;
    private String phoneNum;

    /**
     * Constructor for the User class.
     *
     * @param name     The name of the user.
     * @param email    The email of the user.
     * @param phoneNum The phone number of the user.
     */
    protected User(@NonNull DeviceId id, String name, String email, String phoneNum) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    /**
     * Constructor for the User class (for Parcelable).
     *
     * @param in The Parcel to read from.
     */
    protected User(Parcel in) {
        id = ParcelableUtil.readDeviceId(in);
        name = in.readString();
        email = in.readString();
        phoneNum = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(id, flags);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phoneNum);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Getter for the ID of the user. Filled in by Firestore.
     *
     * @return The ID of the user.
     */
    public DeviceId getId() {
        return id;
    }

    /**
     * Getter for the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the user.
     *
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the email of the user.
     *
     * @param email The email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for the phone number of the user.
     *
     * @return The phone number of the user.
     */
    public String getPhoneNumber() {
        return phoneNum;
    }

    /**
     * TODO: Add validation
     * Setter for the phone number of the user.
     *
     * @param phoneNum The phone number of the user.
     */
    public void setPhoneNumber(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static Builder builder(DeviceId id) {
        return new Builder(id);
    }

    public static Builder builder(String id) {
        return builder(new DeviceId.Builder().setCustomId(id).build());
    }

    /**
     * Builder class for User
     * TODO: Add input validation
     */
    public static class Builder {
        private final DeviceId id;
        private String name;
        private String email;
        private String phoneNumber;

        public Builder(DeviceId id) {
            this.id = id;
        }

        public Builder name(@NonNull String name) {
            this.name = name;
            return this;
        }

        public Builder email(@NonNull String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(@NonNull String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public User build() {
            return new User(id, name, email, phoneNumber);
        }
    }
}
