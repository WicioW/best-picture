package com.project.bestpicture.user.domain;

import com.project.bestpicture.user.api.UserDto;

import java.util.function.Function;

public class UserMapper {

    public static Function<User, UserDto> single =
            t -> {
                var dto = new UserDto();
                dto.username = t.getUsername();
                return dto;
            };
}
