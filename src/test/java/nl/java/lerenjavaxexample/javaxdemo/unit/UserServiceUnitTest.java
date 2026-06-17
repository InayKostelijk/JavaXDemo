package nl.java.lerenjavaxexample.javaxdemo.unit;

import nl.java.lerenjavaxexample.javaxdemo.user.application.UserService;
import nl.java.lerenjavaxexample.javaxdemo.user.application.dto.UserDto;
import nl.java.lerenjavaxexample.javaxdemo.user.application.dto.UserRetrieveDto;
import nl.java.lerenjavaxexample.javaxdemo.user.application.mapper.UserMapper;
import nl.java.lerenjavaxexample.javaxdemo.user.data.UserRepository;
import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;
import nl.java.lerenjavaxexample.javaxdemo.user.presentation.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDto userDto;
    private UserRetrieveDto userRetrieveDto;

    @BeforeEach
    public void setUp() {
        user = new User(
                1L,
                "john",
                "password",
                "john@email.com"
        );

        userDto = new UserDto(
                1L,
                "john",
                "password",
                "john@email.com"
        );

        userRetrieveDto = new UserRetrieveDto(1L, "john", "john@email.com");
    }

    @Test
    public void shouldReturnUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toUserDto(user)).thenReturn(userRetrieveDto);

        UserRetrieveDto result = userService.getUserById(1L);

        assertNotNull(result);
        verify(userRepository).findById(1L);
        verify(userMapper).toUserDto(user);
    }

    @Test
    public void shouldThrowWhenUserNotFound() {
        assertThrows(BadRequestException.class, () -> {
            when(userRepository.findById(1L)).thenReturn(Optional.empty());

            userService.getUserById(1L);
        });
    }

    @Test
    public void shouldCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.createUser(userDto);

        verify(userRepository).save(any(User.class));
    }

    @Test
    public void shouldUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.updateUser(1L, userDto);

        verify(userRepository).findById(1L);
    }

    @Test
    public void shouldThrowWhenUpdatingMissingUser() {
        assertThrows(BadRequestException.class, () -> {
            when(userRepository.findById(1L)).thenReturn(Optional.empty());

            userService.updateUser(1L, userDto);
        });
    }

    @Test
    public void shouldDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository).delete(user);
    }

    @Test
    public void shouldThrowWhenDeletingMissingUser() {
        assertThrows(BadRequestException.class, () -> {
            when(userRepository.findById(1L)).thenReturn(Optional.empty());

            userService.deleteUser(1L);
        });
    }
}