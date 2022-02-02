package com.etiya.weatherApp.business.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchListDto {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean isAdmin;
}
