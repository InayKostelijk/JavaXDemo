package nl.java.lerenjavaxexample.javaxdemo.user.presentation;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions.ResourceNotFoundException;
import nl.java.lerenjavaxexample.javaxdemo.user.application.UserService;
import nl.java.lerenjavaxexample.javaxdemo.user.application.dto.UserDto;
import nl.java.lerenjavaxexample.javaxdemo.user.application.dto.UserRetrieveDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok("Het aanmaken van de gebruiker is gelukt");
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserRetrieveDto> getUser(@Valid @PathVariable Long userId) {
        UserRetrieveDto userById = userService.getUserById(userId);
        if (userById == null) {
            throw new ResourceNotFoundException("User with id " + userId + " not found");
        }
        return ResponseEntity.ok(userById);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@Valid @PathVariable Long userId, @RequestBody UserDto userDto) {
        userService.updateUser(userId, userDto);
        return ResponseEntity.ok("Het updated van de gebruiker is gelukt");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@Valid @PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("Gebruiker is verwijderd");
    }

}
