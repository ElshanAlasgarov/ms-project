package az.spring.travel.msbookings.service;

import az.spring.travel.msbookings.model.dto.request.CreateUserRequest;
import az.spring.travel.msbookings.model.dto.response.UserDto;

public interface UserService {

    UserDto createUser(CreateUserRequest createUserRequest);

    void deleteById(Long id);
}
