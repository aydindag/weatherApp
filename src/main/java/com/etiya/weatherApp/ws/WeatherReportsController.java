package com.etiya.weatherApp.ws;

import com.etiya.weatherApp.business.abstracts.WeatherReportService;
import com.etiya.weatherApp.business.dtos.WeatherReportSearchListDto;
import com.etiya.weatherApp.business.request.weatherReportRequests.CreateWeatherReportRequest;
import com.etiya.weatherApp.business.request.weatherReportRequests.DeleteWeatherReportRequest;
import com.etiya.weatherApp.business.request.weatherReportRequests.UpdateWeatherReportRequest;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/weatherReports")
@CrossOrigin
public class WeatherReportsController {
    private final WeatherReportService weatherReportService;

    @Autowired
    public WeatherReportsController(WeatherReportService weatherReportService) {
        this.weatherReportService = weatherReportService;
    }

    @GetMapping("list")
    public DataResult<List<WeatherReportSearchListDto>> getAll(){
        return this.weatherReportService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateWeatherReportRequest createWeatherReportRequest){
        return  this.weatherReportService.save(createWeatherReportRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteWeatherReportRequest deleteWeatherReportRequest){
        return  this.weatherReportService.delete(deleteWeatherReportRequest);
    }

    @PutMapping("update")
    public  Result update(@RequestBody @Valid UpdateWeatherReportRequest updateWeatherReportRequest){
        return  this.weatherReportService.update(updateWeatherReportRequest);
    }
}
