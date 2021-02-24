package com.learn.mentee.util.deserializer.impl;

import com.learn.mentee.entity.Event;
import com.learn.mentee.util.deserializer.Deserializer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class EventDeserializer implements Deserializer<Event> {

    @Getter
    @Value("${path.resources}${path.events}")
    private String path;

    @Override
    public List<Event> deserialize() {
        List<Event> events = new LinkedList<>();

        Arrays.stream(Optional.ofNullable(new File(getPath()).listFiles())
                .orElse(new File[]{}))
                .forEach(x -> read(x, events));
        return events;
    }

    private void read(File file, List<Event> events) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            events.add((Event) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
