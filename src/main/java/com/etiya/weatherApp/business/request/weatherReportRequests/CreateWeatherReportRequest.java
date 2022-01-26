package com.etiya.weatherApp.business.request.weatherReportRequests;

import com.etiya.weatherApp.business.enums.QueryStatus;
import com.etiya.weatherApp.entities.City;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWeatherReportRequest {

    private String cityName;
    private String email;

}
