package com.learn.mentee.util.deserializer.impl;

import com.learn.mentee.util.deserializer.impl.EventDeserializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventDeserializerTest {

    @Spy
    private EventDeserializer eventDeserializer;

    @Test
    public void deserializeTest() {
        when(eventDeserializer.getPath()).thenReturn("C:/Games/mentee/src/main/resources/entities/events");
        assertFalse(eventDeserializer.deserialize().isEmpty());
    }
}