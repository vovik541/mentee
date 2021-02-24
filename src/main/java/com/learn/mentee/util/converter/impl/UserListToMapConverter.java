package com.learn.mentee.util.converter.impl;

import com.learn.mentee.entity.User;
import com.learn.mentee.util.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserListToMapConverter implements Converter<List<User>, Map<Long, User>> {

    @Override
    public Map<Long, User> convert(List<User> users) {
        return users.stream()
                .collect(Collectors
                        .toMap(User::getId, x -> x));
    }
}
