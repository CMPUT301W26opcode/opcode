package com.example.opcodeapp;

import com.example.opcodeapp.model.Event;
import com.example.opcodeapp.model.User;

import java.time.LocalDateTime;

/**
 * Utility class for generating mock objects
 */
public class MockUtil {

    /**
     * Creates a mock user with a simple user id
     *
     * @param id simple id number
     * @return a new user instance
     */
    public static User mockUser(int id) {
        return User.builder("mock_device_id_" + id)
                .name("User " + id)
                .email("mock_user" + id + "@email.com")
                .phoneNumber("(123) 456-7890")
                .build();
    }

    /**
     * Creates a mock event with a simple event id
     *
     * @param id simple id number
     * @return a new user instance
     */
    public static Event mockEvent(int id) {
        return Event.builder("event_id" + id)
                .location("Edmonton, AB")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
)
                .startDate(

                        LocalDateTime.of(2026, 3, 15, 0, 0, 0),
                        )
                        .

                LocalDateTime.now(),
        LocalDateTime.of(2026, 3, 15, 0, 0, 0),
                LocalDateTime.of(2026, 3, 14, 16, 0),
                mockUser(1000).getId()
        );
    }
}
