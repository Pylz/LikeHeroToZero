package com.pylz.dao;

import com.pylz.entities.User;

public interface UserDao extends GenericDao<User> {
    User findByUsername(String username);
}
