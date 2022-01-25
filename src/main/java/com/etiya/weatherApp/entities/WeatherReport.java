package com.etiya.weatherApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="weather_reports")
public class WeatherReport {
    @Id
    private String weatherReportId;
    private String weatherCondition;
    private double temperature;
    private long queryTime;
    private Date queryDate;
    private String ipAddress;
    private String queryStatusEnum;
    private User user;
    private City city;
}
