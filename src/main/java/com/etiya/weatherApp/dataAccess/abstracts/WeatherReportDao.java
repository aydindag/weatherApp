package com.etiya.weatherApp.dataAccess.abstracts;

import com.etiya.weatherApp.entities.WeatherReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherReportDao extends MongoRepository<WeatherReport, String> {
}
