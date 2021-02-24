package com.learn.mentee.service.impl;

import com.learn.mentee.dao.UserDao;
import com.learn.mentee.entity.User;
import com.learn.mentee.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(long userId) {
        return userDao.getUserMap().getOrDefault(userId, new User());
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserMap().values().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(new User());
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        if (pageSize < 1 || pageNum < 1) {
            return Collections.emptyList();
        }

        int from = pageSize * (pageNum - 1);
        int to = pageSize * pageNum;

        List<User> usersByName = userDao.getUserMap().values().stream()
                .filter(u -> u.getName().equals(name))
                .collect(Collectors.toList());

        return usersByName.subList(from, Math.min(to, usersByName.size()));
    }

    @Override
    public User createUser(User user) {
        userDao.getUserMap().keySet().stream()
                .max(Long::compareTo)
                .ifPresent(x -> user.setId(x + 1));
        return userDao.getUserMap().putIfAbsent(user.getId(), user);
    }

    @Override
    public User updateUser(User user) {
        return Optional
                .ofNullable(userDao
                        .getUserMap()
                        .computeIfPresent(user.getId(), (k, v) -> {
                            v.setName(user.getName());
                            v.setEmail(user.getEmail());
                            return v;
                        }))
                .orElse(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userDao.getUserMap().remove(userId) != null;
    }
}