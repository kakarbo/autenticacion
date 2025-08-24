package co.com.crediya.r2dbc.service;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.service.EmailValidate;
import org.springframework.stereotype.Service;
import co.com.crediya.model.user.service.ValidateUserData;

import java.math.BigDecimal;

@Service
public class ValidateUserDataImpl implements ValidateUserData {

    @Override
    public void validateData(User user) {
        if(user.getNombre() == null || user.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        if(user.getApellido() == null || user.getApellido().isBlank())
            throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
        if(user.getCorreoElectronico() == null || EmailValidate.isValid(user.getCorreoElectronico()))
            throw new IllegalArgumentException("Correo electronico inválido");
        if(user.getSalarioBase() == null || user.getSalarioBase().compareTo(BigDecimal.ZERO) <= 0 && user.getSalarioBase().compareTo(new BigDecimal("15000000")) >= 0)
            throw new IllegalArgumentException("El salario base debe ser mayor a cero y menor a 15000000");
    }
}
