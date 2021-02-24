package com.learn.mentee.service;

import com.learn.mentee.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    /**
     * Gets user by its id.
     * @return User or empty user object if user was not found.
     */
    User getUserById(long userId);

    /**
     * Gets user by its email.
     * @return User or empty user object is user was not found.
     */
    User getUserByEmail(String email);

    /**
     * Get list of users by matching name.
     * @param name Users name.
     * @param pageSize Pagination param. Number of users to return on a page.
     * @param pageNum Pagination param. Number of the page to return. Starts from 1.
     * @return List of users or empty list if nothing was found.
     */
    List<User> getUsersByName(String name, int pageSize, int pageNum);

    /**
     * Creates new user.
     * @param user User data.
     * @return null if user was created.
     */
    User createUser(User user);

    /**
     * Updates user using given data.
     * @param user User data for update. Should have id set.
     * @return Updated User object.
     */
    User updateUser(User user);

    /**
     * Deletes user by its id.
     * @param userId User id.
     * @return Flag that shows whether user has been deleted.
     */
    boolean deleteUser(long userId);
}
