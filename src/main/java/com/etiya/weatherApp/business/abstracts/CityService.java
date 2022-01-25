package com.etiya.weatherApp.business.abstracts;


import com.etiya.weatherApp.business.dtos.CitySearchListDto;
import com.etiya.weatherApp.business.request.cityRequests.CreateCityRequest;
import com.etiya.weatherApp.business.request.cityRequests.DeleteCityRequest;
import com.etiya.weatherApp.business.request.cityRequests.UpdateCityRequest;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;

import java.util.List;

public interface CityService {
    DataResult<List<CitySearchListDto>> getAll();
    Result save(CreateCityRequest createCityRequest);
    Result delete(DeleteCityRequest deleteCityRequest);
    Result update(UpdateCityRequest updateCityRequest);
    Result existByCityId(String cityId);
}

