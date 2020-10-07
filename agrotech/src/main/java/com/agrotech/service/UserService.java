package com.agrotech.service;

import com.agrotech.model.User;

public interface UserService extends BaseEntityService<User> {
    User findByUsername(String username);

    public Long count();
}
