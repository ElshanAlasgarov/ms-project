package az.spring.travel.msbookings.service.impl;


import az.spring.travel.msbookings.domain.entity.UserEntity;
import az.spring.travel.msbookings.domain.repository.UserRepository;
import az.spring.travel.msbookings.exception.AlreadyExistsException;
import az.spring.travel.msbookings.exception.NotFoundException;
import az.spring.travel.msbookings.mapper.UserMapper;
import az.spring.travel.msbookings.model.dto.request.CreateUserRequest;
import az.spring.travel.msbookings.model.dto.response.UserDto;
import az.spring.travel.msbookings.model.enums.UserStatus;
import az.spring.travel.msbookings.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        checkIfFlightExists(createUserRequest.getEmail());
        UserEntity userEntity = userMapper.toUserEntity(createUserRequest);
        UserEntity savedEntity = userRepository.save(userEntity);
        return userMapper.toUserDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User with id" + id + " not found");
        }
        //soft-delete
        userRepository.findById(id)
                .ifPresent(userEntity -> {
                    userEntity.setStatus(UserStatus.DELETED);
                    userRepository.save(userEntity);
                });

        //hard-delete
//        userRepository.deleteById(id);

    }

    private void checkIfFlightExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("User already exists with email: " + email);
        }
    }
}
