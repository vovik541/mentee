package com.learn.mentee.util.deserializer.impl;

import com.learn.mentee.entity.User;
import com.learn.mentee.util.deserializer.Deserializer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class UserDeserializer implements Deserializer<User> {

    @Getter
    @Value("${path.resources}${path.users}")
    private String path;

    @Override
    public List<User> deserialize() {
        List<User> users = new ArrayList<>();
        Arrays.stream(Optional.ofNullable(new File(getPath()).listFiles())
                .orElse(new File[]{}))
                .forEach(x -> read(x, users));
        return users;
    }

    private void read(File file, List<User> users) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            users.add((User) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
