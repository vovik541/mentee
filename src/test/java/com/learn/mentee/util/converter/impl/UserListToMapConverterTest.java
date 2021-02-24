package com.learn.mentee.util.converter.impl;

import com.learn.mentee.entity.User;
import com.learn.mentee.util.converter.impl.UserListToMapConverter;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserListToMapConverterTest {

    @Test
    public void convertTest() {
        UserListToMapConverter converter = new UserListToMapConverter();
        User user = new User();
        Map<Long, User> expectedMap = new HashMap<>();
        expectedMap.put(user.getId(), user);
        Map<Long, User> actualMap = converter.convert(Collections.singletonList(user));
        assertEquals(expectedMap, actualMap);
    }
}