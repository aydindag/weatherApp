package com.etiya.weatherApp.business.request.weatherReportRequests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteWeatherReportRequest {
    @NotNull
    private String weatherReportId;
}
