package com.etiya.weatherApp.business.abstracts;

import com.etiya.weatherApp.business.request.LoginRequests.LoginRequest;
import com.etiya.weatherApp.core.utilities.results.Result;

public interface LoginService {
    Result login(LoginRequest loginRequest);
}
