package com.etiya.weatherApp.dataAccess.abstracts;

import com.etiya.weatherApp.entities.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends MongoRepository<City,String> {

    City findByCityName(String cityName);
}
