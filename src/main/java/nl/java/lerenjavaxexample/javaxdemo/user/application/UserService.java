package nl.java.lerenjavaxexample.javaxdemo.user.application;

import nl.java.lerenjavaxexample.javaxdemo.user.application.dto.UserDto;
import nl.java.lerenjavaxexample.javaxdemo.user.application.dto.UserRetrieveDto;
import nl.java.lerenjavaxexample.javaxdemo.user.application.mapper.UserMapper;
import nl.java.lerenjavaxexample.javaxdemo.user.data.UserRepository;
import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;
import nl.java.lerenjavaxexample.javaxdemo.user.presentation.exception.BadRequestException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserRetrieveDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"));

        return userMapper.toUserDto(user);
    }

    public void createUser(UserDto userDto) {

        User user = new User(
                null,
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail()
        );

        userRepository.save(user);
    }

    public void updateUser(Long id, UserDto userDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"));

        user.updateUser(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail()
        );
    }

    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"));

        userRepository.delete(user);
    }
}