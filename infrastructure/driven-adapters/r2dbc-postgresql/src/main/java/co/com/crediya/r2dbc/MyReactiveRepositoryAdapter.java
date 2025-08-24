package co.com.crediya.r2dbc;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import co.com.crediya.r2dbc.dto.UserEntity;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Repository
public class MyReactiveRepositoryAdapter implements UserRepository {

    private final R2dbcEntityTemplate template;

    public MyReactiveRepositoryAdapter(R2dbcEntityTemplate template) {
        this.template = template;
    }

    @Override
    public Mono<User> save(User user) {
        return template.insert(UserEntity.class)
                .using(UserEntity.fromDomain(user))
                .map(UserEntity::toDomain);
    }

    @Override
    public Mono<Boolean> availableByMail(String email) {
        return template.select(UserEntity.class)
                .matching(query(where("correoElectronico").is(email)))
                .one()
                .map(u -> false)
                .defaultIfEmpty(true);
    }

    @Override
    public Mono<User> register(User user) {
        return Mono.just(user);
    }
}
