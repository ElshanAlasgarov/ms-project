package az.spring.travel.msbookings.mapper;

import az.spring.travel.msbookings.domain.entity.UserEntity;
import az.spring.travel.msbookings.model.dto.request.CreateUserRequest;
import az.spring.travel.msbookings.model.dto.response.UserDto;
import az.spring.travel.msbookings.model.enums.UserStatus;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toUserEntity(CreateUserRequest createUserRequest) {
        return UserEntity.builder()
                .email(createUserRequest.getEmail())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .status(UserStatus.valueOf(createUserRequest.getStatus()))
                .build();
    }

    public UserDto toUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phoneNumber(userEntity.getPhoneNumber())
                .status(UserStatus.ACTIVE)
                .build();
    }
}
