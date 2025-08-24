package co.com.crediya.api;
import co.com.crediya.api.dto.UserRequest;
import co.com.crediya.api.dto.UserResponse;
import co.com.crediya.model.user.User;
import co.com.crediya.usecase.registeruser.RegisterUserUseCase;
//import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
public class ApiRest {

    private final RegisterUserUseCase registerUserUseCase;

    public ApiRest(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> registerUser(@RequestBody UserRequest request) {

        User user = new User(
                request.idUser(),
                request.nombre(),
                request.apellido(),
                request.fechaNacimiento(),
                request.direccion(),
                request.telefono(),
                request.correoElectronico(),
                request.salarioBase()
        );

        return registerUserUseCase.ejecute(user)
            .map(savedUser -> {
                Map<String, Object> body = new HashMap<>();
                body.put("mensaje", "Usuario registrado exitosamente");
                body.put("idUsuario", savedUser.getIdUser());
                return ResponseEntity.ok(body);
            })
                .onErrorResume(e -> {
                    Map<String, Object> error = new HashMap<>();
                    error.put("mensaje", "Error al registrar usuario");
                    //error.put("detalle", e.getMessage());
                    return Mono.just(ResponseEntity.badRequest().body(error));
                });
    }
}
