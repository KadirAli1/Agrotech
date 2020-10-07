package com.agrotech.service;

import java.util.Collection;
import java.util.Optional;

public interface BaseEntityService<T> {
    Optional<T> findById(Long id);

    T findOne(Long id);

    Collection<T> findAll();

    T save(T entity);
}
