package com.etiya.weatherApp.business.request.weatherReportRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWeatherReportRequest {

    private String cityName;
    private String email;

}
