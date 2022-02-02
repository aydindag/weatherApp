package com.etiya.weatherApp.ws;

import com.etiya.weatherApp.business.abstracts.CityService;
import com.etiya.weatherApp.business.dtos.CitySearchListDto;
import com.etiya.weatherApp.business.request.cityRequests.CreateCityRequest;
import com.etiya.weatherApp.business.request.cityRequests.DeleteCityRequest;
import com.etiya.weatherApp.business.request.cityRequests.UpdateCityRequest;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/cities")
@CrossOrigin
public class CitiesController {
    private CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("list")
    public DataResult<List<CitySearchListDto>> getAll(){
        return this.cityService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCityRequest createCityRequest){
    return this.cityService.save(createCityRequest);
    }

    @DeleteMapping("delete/{cityId}")
    public  Result delete(@PathVariable(name = "cityId") @Valid String cityId){
        return this.cityService.delete(cityId);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest){
        return  this.cityService.update(updateCityRequest);
    }
}
