package com.agrotech.repository;

import java.util.Collection;
import java.util.Optional;

public interface BaseRepository<T,S> {
    Collection<T> findAll();
    Optional<T> findById(S id);

    T save(T t);
}
