package com.etiya.weatherApp.ws;

import com.etiya.weatherApp.business.abstracts.LoginService;
import com.etiya.weatherApp.business.request.LoginRequests.LoginRequest;
import com.etiya.weatherApp.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/login")
@CrossOrigin
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    Result login(@Valid @RequestBody LoginRequest loginRequest){
        return this.loginService.login(loginRequest);
    }
}
