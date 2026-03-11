package com.example.opcodeapp.enums;

import java.util.Map;

/**
 * Represents the status of applicants to an {@link com.example.opcodeapp.model.Event}
 */
public enum ApplicantStatus {
    NOT_DRAWN(0),
    INVITED(1),
    ACCEPTED(2),
    DECLINED(3);

    private final int id;

    ApplicantStatus(int id) {
        this.id = id;
    }

    private static final Map<Integer, ApplicantStatus> map = Map.of(
            0, NOT_DRAWN,
            1, INVITED,
            2, ACCEPTED,
            3, DECLINED
    );

    public static ApplicantStatus fromValue(int i) {
        return map.getOrDefault(i, null);
    }

    public int getId() {
        return id;
    }
}
