package com.etiya.weatherApp.business.concretes;

import com.etiya.weatherApp.business.abstracts.CityService;
import com.etiya.weatherApp.business.dtos.CitySearchListDto;
import com.etiya.weatherApp.business.request.cityRequests.CreateCityRequest;
import com.etiya.weatherApp.business.request.cityRequests.DeleteCityRequest;
import com.etiya.weatherApp.business.request.cityRequests.UpdateCityRequest;
import com.etiya.weatherApp.core.utilities.business.BusinessRules;
import com.etiya.weatherApp.core.utilities.mapping.ModelMapperService;
import com.etiya.weatherApp.core.utilities.results.*;
import com.etiya.weatherApp.dataAccess.abstracts.CityDao;
import com.etiya.weatherApp.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {
    private CityDao cityDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CitySearchListDto>> getAll() {
        List<City> result = this.cityDao.findAll();
        List<CitySearchListDto> response = result.stream().map(city -> modelMapperService.forDto()
                .map(city, CitySearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CitySearchListDto>>(response);
    }

    @Override
    public Result save(CreateCityRequest createCityRequest) {
        City city = modelMapperService.forRequest().map(createCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessResult("City added");
    }

    @Override
    public Result delete(DeleteCityRequest deleteCityRequest) {
        Result result = BusinessRules.run(existByCityId(deleteCityRequest.getCityId()));

        if(result != null){
            return  result;
        }
        City city = modelMapperService.forRequest().map(deleteCityRequest, City.class);
        this.cityDao.delete(city);
        return new SuccessResult("City deleted");
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequest) {
        Result result = BusinessRules.run(existByCityId(updateCityRequest.getCityId()));

        if(result != null){
            return  result;
        }
        City city =  modelMapperService.forRequest().map(updateCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessResult("City updated");
    }

    @Override
    public Result existByCityId(String cityId) {
        if(this.cityDao.existsById(cityId)){
            return new SuccessResult();
        }
        return new ErrorResult("Couldnt find city according to this cityId");
    }
}
