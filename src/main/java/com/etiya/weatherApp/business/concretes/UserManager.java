package com.etiya.weatherApp.business.concretes;

import com.etiya.weatherApp.business.abstracts.UserService;
import com.etiya.weatherApp.business.dtos.UserSearchListDto;
import com.etiya.weatherApp.business.request.userRequests.CreateUserRequest;
import com.etiya.weatherApp.business.request.userRequests.DeleteUserRequest;
import com.etiya.weatherApp.business.request.userRequests.UpdateUserRequest;
import com.etiya.weatherApp.core.utilities.business.BusinessRules;
import com.etiya.weatherApp.core.utilities.mapping.ModelMapperService;
import com.etiya.weatherApp.core.utilities.results.*;
import com.etiya.weatherApp.dataAccess.abstracts.UserDao;
import com.etiya.weatherApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {
    private ModelMapperService modelMapperService;
    private UserDao userDao;

    @Autowired
    public UserManager(ModelMapperService modelMapperService, UserDao userDao) {
        this.modelMapperService = modelMapperService;
        this.userDao = userDao;
    }



    @Override
    public DataResult<List<UserSearchListDto>> getAll() {
        List<User> result = userDao.findAll();
        List<UserSearchListDto> response = result.stream().map(user -> modelMapperService.forDto()
                .map(user, UserSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<UserSearchListDto>>(response);
    }

    @Override
    public Result save(CreateUserRequest createUserRequest) {
        Result result = BusinessRules.run(exitsByEmail(createUserRequest.getEmail()));

        if(result != null){
            return result;
        }
        User user = modelMapperService.forRequest().map(createUserRequest, User.class);
        this.userDao.save(user);
        return new SuccessResult("User added");
    }

    @Override
    public Result delete(DeleteUserRequest deleteUserRequest) {
        Result result = BusinessRules.run(exitsByUserId(deleteUserRequest.getUserId()));

        if(result != null){
            return  result;
        }
        User user = modelMapperService.forRequest().map(deleteUserRequest, User.class);
        this.userDao.delete(user);
        return new SuccessResult("User deleted");
    }

    @Override
    public Result update(UpdateUserRequest updateUserRequest) {
        Result result = BusinessRules.run(exitsByUserId(updateUserRequest.getUserId()));

        if(result != null){
            return  result;
        }
        User user = modelMapperService.forRequest().map(updateUserRequest, User.class);
        this.userDao.save(user);
        return new SuccessResult("User updated");
    }

    @Override
    public Result exitsByEmail(String email) {
        if(this.userDao.existsByEmail(email)){
            return new ErrorResult("This email already taken");
        }
        return new SuccessResult();
    }

    @Override
    public Result exitsByUserId(String userId) {
        if(this.userDao.existsById(userId)){
            return  new SuccessResult();
        }
        return new ErrorResult("Couldn't find user according to this user id");
    }
}
