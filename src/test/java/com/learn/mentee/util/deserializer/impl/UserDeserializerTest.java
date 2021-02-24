package com.learn.mentee.util.deserializer.impl;

import com.learn.mentee.util.deserializer.impl.UserDeserializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDeserializerTest {

    @Spy
    private UserDeserializer userDeserializer;

    @Test
    public void deserializeTest() {
        when(userDeserializer.getPath()).thenReturn("C:/Games/mentee/src/main/resources/entities/users");
        assertFalse(userDeserializer.deserialize().isEmpty());
    }
}