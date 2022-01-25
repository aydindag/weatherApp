package com.etiya.weatherApp.business.concretes;

import com.etiya.weatherApp.business.abstracts.WeatherApiCallService;
import com.etiya.weatherApp.business.abstracts.WeatherReportService;
import com.etiya.weatherApp.business.dtos.WeatherApiResponse;
import com.etiya.weatherApp.business.dtos.WeatherReportSearchListDto;
import com.etiya.weatherApp.business.request.weatherReportRequests.CreateWeatherReportRequest;
import com.etiya.weatherApp.business.request.weatherReportRequests.DeleteWeatherReportRequest;
import com.etiya.weatherApp.business.request.weatherReportRequests.UpdateWeatherReportRequest;
import com.etiya.weatherApp.core.utilities.ExecutionTime;
import com.etiya.weatherApp.core.utilities.mapping.ModelMapperService;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import com.etiya.weatherApp.core.utilities.results.SuccessDataResult;
import com.etiya.weatherApp.core.utilities.results.SuccessResult;
import com.etiya.weatherApp.dataAccess.abstracts.CityDao;
import com.etiya.weatherApp.dataAccess.abstracts.UserDao;
import com.etiya.weatherApp.dataAccess.abstracts.WeatherReportDao;
import com.etiya.weatherApp.entities.City;
import com.etiya.weatherApp.entities.User;
import com.etiya.weatherApp.entities.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherReportManager implements WeatherReportService {
    private final ModelMapperService modelMapperService;
    private final WeatherReportDao weatherReportDao;
    private final CityDao cityDao;
    private final UserDao userDao;
    private final WeatherApiCallService weatherApiCallService;


    @Autowired
    public WeatherReportManager(ModelMapperService modelMapperService, WeatherReportDao weatherReportDao, CityDao cityDao, UserDao userDao, WeatherApiCallService weatherApiCallService) {
        this.modelMapperService = modelMapperService;
        this.weatherReportDao = weatherReportDao;
        this.cityDao = cityDao;
        this.userDao = userDao;
        this.weatherApiCallService = weatherApiCallService;
    }

    @Override
    public DataResult<List<WeatherReportSearchListDto>> getAll() {
        List<WeatherReport> result = weatherReportDao.findAll();
        List<WeatherReportSearchListDto> response = result.stream().map(weatherReport -> modelMapperService.forDto()
                .map(weatherReport, WeatherReportSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<WeatherReportSearchListDto>>(response);
    }

    @Override
    public Result save(CreateWeatherReportRequest createWeatherReportRequest) {
        ExecutionTime executionTime = new ExecutionTime(true);
        WeatherReport weatherReport = modelMapperService.forRequest().map(createWeatherReportRequest, WeatherReport.class);

        City city = cityDao.findByCityName(createWeatherReportRequest.getCityName());
        WeatherApiResponse apiResponse = weatherApiCallService.getWeatherResult(createWeatherReportRequest.getCityName());
        if(apiResponse.getWeather().length != 0 && apiResponse.getMain() != null){
            weatherReport.setTemperature(apiResponse.getMain().getTemp()-273.15);
            weatherReport.setWeatherCondition(apiResponse.getWeather()[0].getDescription());
        }

        weatherReport.setCity(city);

        User user  = userDao.findByEmail(createWeatherReportRequest.getEmail());
        weatherReport.setUser(user);
        weatherReport.setQueryDate(new java.util.Date());

        executionTime.endTask();
        weatherReport.setQueryTime(executionTime.duration());

        this.weatherReportDao.save(weatherReport);
        return new SuccessResult("Weather added");
    }

    @Override
    public Result delete(DeleteWeatherReportRequest deleteWeatherReportRequest) {
        WeatherReport weatherReport = modelMapperService.forRequest().map(deleteWeatherReportRequest, WeatherReport.class);
        this.weatherReportDao.delete(weatherReport);
        return new SuccessResult("Weather deleted");
    }

    @Override
    public Result update(UpdateWeatherReportRequest updateWeatherReportRequest) {
        WeatherReport weatherReport = modelMapperService.forRequest().map(updateWeatherReportRequest, WeatherReport.class);
        this.weatherReportDao.save(weatherReport);
        return new SuccessResult("Weather updated");
    }

}
