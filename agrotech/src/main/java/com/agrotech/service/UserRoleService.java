package com.agrotech.service;

import com.agrotech.model.UserRole;

import java.util.List;

public interface UserRoleService {
    public List<UserRole> findAll();

    public List<UserRole> findAllByOrderByIdDesc();
}
