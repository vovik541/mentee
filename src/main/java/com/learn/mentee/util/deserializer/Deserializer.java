package com.learn.mentee.util.deserializer;

import com.learn.mentee.entity.User;

import java.util.List;

public interface Deserializer<T> {
    /**
     * Deserializes objects from file (path is defined at application properties file).
     * @return List of objects from file.
     */
    List<T> deserialize();
}
