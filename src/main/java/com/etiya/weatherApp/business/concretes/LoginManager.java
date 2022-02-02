package com.etiya.weatherApp.business.concretes;

import com.etiya.weatherApp.business.abstracts.LoginService;
import com.etiya.weatherApp.business.abstracts.UserService;
import com.etiya.weatherApp.business.request.LoginRequests.LoginRequest;
import com.etiya.weatherApp.core.utilities.business.BusinessRules;
import com.etiya.weatherApp.core.utilities.results.ErrorResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import com.etiya.weatherApp.core.utilities.results.SuccessResult;
import com.etiya.weatherApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginManager implements LoginService {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Autowired
    public LoginManager(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Result login(LoginRequest loginRequest) {
        Result result = BusinessRules.run(this.checkUserByPassword(loginRequest));

        if(result != null){
            return  result;
        }
        return new SuccessResult("Logged successfully");
    }

    private Result checkUserByPassword(LoginRequest loginRequest){
        User user = this.userService.getByEmail(loginRequest.getEmail()).getData();
        if (user == null){
            return new ErrorResult("Wrong Emaill");
        }

        if(!this.bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            return new ErrorResult("Wrong Password");
        }
        return  new SuccessResult();
    }

}
