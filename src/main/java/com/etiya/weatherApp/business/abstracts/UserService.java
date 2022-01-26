package com.etiya.weatherApp.business.abstracts;

import com.etiya.weatherApp.business.dtos.UserSearchListDto;
import com.etiya.weatherApp.business.request.userRequests.CreateUserRequest;
import com.etiya.weatherApp.business.request.userRequests.DeleteUserRequest;
import com.etiya.weatherApp.business.request.userRequests.UpdateUserRequest;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import com.etiya.weatherApp.entities.User;

import java.util.List;

public interface UserService {
    DataResult<List<UserSearchListDto>> getAll();
    Result save(CreateUserRequest createUserRequest);
    Result delete(DeleteUserRequest deleteUserRequest);
    Result update(UpdateUserRequest updateUserRequest);
    Result exitsByEmail(String email);
    Result exitsByUserId(String userId);
    DataResult<User> getByEmail(String email);
}
