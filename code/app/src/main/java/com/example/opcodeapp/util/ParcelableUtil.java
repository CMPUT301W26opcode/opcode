package com.example.opcodeapp.util;

import android.companion.DeviceId;
import android.os.Parcel;

import java.time.LocalDateTime;

public class ParcelableUtil {

    public static DeviceId readDeviceId(Parcel in) {
        return in.readParcelable(DeviceId.class.getClassLoader(), DeviceId.class);
    }

    public static LocalDateTime readLocalDateTime(Parcel in) {
        return in.readSerializable(LocalDateTime.class.getClassLoader(), LocalDateTime.class);
    }
}
