package com.learn.mentee.dao;

import com.learn.mentee.entity.User;
import com.learn.mentee.util.deserializer.impl.UserDeserializer;
import com.learn.mentee.util.converter.impl.UserListToMapConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserDao {

    @Getter
    private Map<Long, User> userMap;

    @Resource
    private UserListToMapConverter converter;

    @Resource
    private UserDeserializer deserializer;

    @PostConstruct
    public void init() {
        userMap = converter.convert(deserializer.deserialize());
        for (long i = 4; i <= 15; i++) {
            User u = new User();
            u.setId(i);
            u.setName("user");
            u.setEmail("someMail");
            userMap.put(u.getId(), u);
        }
    }
}