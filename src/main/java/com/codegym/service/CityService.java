package com.codegym.service;

import com.codegym.model.City;
import org.springframework.data.domain.Page;

public interface CityService {
    Iterable<City> findAll();
    City findById(Long id);
    void save(City city);
    void remove(Long id);
}
