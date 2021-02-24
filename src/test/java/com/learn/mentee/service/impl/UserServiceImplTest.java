package com.learn.mentee.service.impl;

import com.learn.mentee.dao.UserDao;
import com.learn.mentee.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDaoMock;

    @Mock
    private User userMock;

    @Spy
    private HashMap<Long, User> userMapSpy;

    private final long id = 2L;
    private final String email = "e";
    private final String name = "n";

    @Before
    public void setUp() {
        when(userDaoMock.getUserMap()).thenReturn(userMapSpy);
        when(userMapSpy.get(anyLong())).thenReturn(userMock);
        when(userMock.getEmail()).thenReturn(email);
        when(userMock.getName()).thenReturn(name);
        when(userMock.getId()).thenReturn(id);
        userMapSpy.put(id, userMock);
    }

    @Test
    public void getUserByIdTest() {
        assertEquals(userMock, userService.getUserById(id));
    }

    @Test
    public void getUserByExistentEmailTest() {
        assertEquals(userMock, userService.getUserByEmail(email));
    }

    @Test
    public void getUserByNonExistentEmailTest() {
        assertEquals(new User(), userService.getUserByEmail("a"));
    }

    @Test
    public void getUsersByNameWithInvalidInputTest() {
        assertTrue(userService.getUsersByName(name, -1, -1).isEmpty());
    }

    @Test
    public void getUserByNameWithValidInputTest() {
        assertFalse(userService.getUsersByName(name, 1, 1).isEmpty());
    }

    @Test
    public void createNonExistentUserTest() {
        assertNull(userService.createUser(new User()));
    }

    @Test
    public void updateUserTest() {
        assertEquals(userMock, userService.updateUser(userMock));
    }

    @Test
    public void deleteUserTest() {
        assertTrue(userService.deleteUser(id));
    }
}