package co.com.crediya.model.user.gateways;

import co.com.crediya.model.user.User;

public interface UserRepository {
    boolean availableByMail(String email);
    User register(User user);
}
