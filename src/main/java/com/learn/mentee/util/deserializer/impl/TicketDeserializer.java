package com.learn.mentee.util.deserializer.impl;

import com.learn.mentee.entity.Ticket;
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
public class TicketDeserializer implements Deserializer<Ticket> {

    @Getter
    @Value("${path.resources}${path.tickets}")
    private String path;


    @Override
    public List<Ticket> deserialize() {
        List<Ticket> tickets = new LinkedList<>();
        Arrays.stream(Optional.ofNullable(new File(getPath()).listFiles())
                .orElse(new File[]{}))
                .forEach(x -> read(x, tickets));
        return tickets;
    }

    private void read(File file, List<Ticket> tickets) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tickets.add((Ticket) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
