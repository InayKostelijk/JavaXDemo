package nl.java.lerenjavaxexample.javaxdemo.user.application.mapper;

import nl.java.lerenjavaxexample.javaxdemo.user.application.dto.UserRetrieveDto;
import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserRetrieveDto toUserDto(User user) {

        if (user == null) {
            return null;
        }

        return new UserRetrieveDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}