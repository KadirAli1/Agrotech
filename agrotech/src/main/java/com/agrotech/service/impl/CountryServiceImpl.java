package com.agrotech.service.impl;

import com.agrotech.model.Country;
import com.agrotech.repository.jpa.CountryRepository;
import com.agrotech.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository repo;

    @Override
    public List<Country> findAll() { return repo.findAll(); }
}
