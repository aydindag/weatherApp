package com.etiya.weatherApp.business.request.weatherReportRequests;

import com.etiya.weatherApp.business.enums.QueryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWeatherReportRequest {

    @NotNull
    private String weatherReportId;
    @NotNull
    private String weatherCondition;
    @NotNull
    private String temperature;

    @NotNull
    private String queryStatusEnum;

    @NotNull
    private String cityId;
}
