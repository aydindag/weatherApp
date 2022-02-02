package com.etiya.weatherApp.ws;

import com.etiya.weatherApp.business.abstracts.UserService;
import com.etiya.weatherApp.business.dtos.UserSearchListDto;
import com.etiya.weatherApp.business.request.userRequests.CreateUserRequest;
import com.etiya.weatherApp.business.request.userRequests.DeleteUserRequest;
import com.etiya.weatherApp.business.request.userRequests.UpdateUserRequest;
import com.etiya.weatherApp.core.utilities.results.DataResult;
import com.etiya.weatherApp.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("list")
    private DataResult<List<UserSearchListDto>> getAll() {

        return this.userService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateUserRequest createUserRequest){
        return this.userService.save(createUserRequest);
    }

    @PostMapping("delete")
    public Result delete(@RequestBody @Valid DeleteUserRequest deleteUserRequest){
        return this.userService.delete(deleteUserRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest){
        return  this.userService.update(updateUserRequest);
    }
}
