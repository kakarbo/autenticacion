package co.com.crediya.usecase.registeruser;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import co.com.crediya.model.user.service.ValidateUserData;
import reactor.core.publisher.Mono;

//import lombok.RequiredArgsConstructor;
//@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final UserRepository userRepository;
    private final ValidateUserData validateUserData;

    public RegisterUserUseCase(UserRepository userRepository, ValidateUserData validateUserData) {
        this.userRepository = userRepository;
        this.validateUserData = validateUserData;
    }

    public Mono<User> ejecute(User user) {
        validateUserData.validateData(user);
        return userRepository.save(user)
                .map(savedUser -> {
                    return savedUser;
                });
    }
}
