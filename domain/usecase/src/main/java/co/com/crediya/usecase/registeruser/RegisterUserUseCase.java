package co.com.crediya.usecase.registeruser;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
//import lombok.RequiredArgsConstructor;
//@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final UserRepository userRepository;

    public RegisterUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User ejecute(User user) throws IllegalAccessException {
        user.validateData();

        if(userRepository.availableByMail(user.getCorreoElectronico()))
            throw new IllegalAccessException("El correo ya está registrado");
        return userRepository.register(user);
    }
}
