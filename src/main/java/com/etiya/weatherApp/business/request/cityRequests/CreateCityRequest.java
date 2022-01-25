package com.etiya.weatherApp.business.request.cityRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {

    @JsonIgnore
    private String cityId;

    @NotNull
    private String cityName;
}
