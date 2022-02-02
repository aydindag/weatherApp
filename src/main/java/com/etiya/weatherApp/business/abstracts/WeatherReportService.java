package com.etiya.weatherApp.business.abstracts;


import com.etiya.weatherApp.business.dtos.WeatherReportSearchListDto;
import com.etiya.weatherApp.business.request.weatherReportRequests.CreateWeatherReportRequest;
import com.etiya.weatherApp.business.request.weatherReportRequests.DeleteWeatherReportRequest;
import com.etiya.weatherApp.business.request.weatherReportRequests.UpdateWeatherReportRequest;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import com.etiya.weatherApp.entities.WeatherReport;

import java.util.List;

public interface WeatherReportService {
    DataResult<List<WeatherReportSearchListDto>> getAll();
    DataResult<WeatherReportSearchListDto> save(CreateWeatherReportRequest createWeatherReportRequest);
    Result delete(DeleteWeatherReportRequest deleteWeatherReportRequest);
    Result update(UpdateWeatherReportRequest updateWeatherReportRequest);

}
