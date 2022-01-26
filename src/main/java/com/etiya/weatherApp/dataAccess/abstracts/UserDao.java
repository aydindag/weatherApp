package com.etiya.weatherApp.dataAccess.abstracts;

import com.etiya.weatherApp.entities.City;
import com.etiya.weatherApp.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User getByEmail(String email);
}
