package com.agrotech.service.impl;

import com.agrotech.model.UserRole;
import com.agrotech.repository.jpa.UserRoleRepository;
import com.agrotech.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRoleServiceImpl  implements UserRoleService {
    @Autowired
    private UserRoleRepository repo;

    @Override
    public List<UserRole> findAll() {
        return repo.findAll();
    }

    @Override
    public List<UserRole> findAllByOrderByIdDesc() { return repo.findAllByOrderByIdDesc(); }

}
