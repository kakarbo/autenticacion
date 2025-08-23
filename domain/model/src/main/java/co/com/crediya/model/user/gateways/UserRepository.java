package co.com.crediya.model.user.gateways;

import co.com.crediya.model.user.User;

public interface UserRepository {
    void availableByMail(String email);
    User register(User user);
}
