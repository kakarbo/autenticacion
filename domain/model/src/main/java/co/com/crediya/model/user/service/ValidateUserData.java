package co.com.crediya.model.user.service;

import java.math.BigDecimal;

public class ValidateUserData {
    public void validateData(String nombre, String apellido, String email, BigDecimal salarioBase) throws IllegalAccessException {
        if(nombre == null || nombre.isBlank())
            throw new IllegalAccessException("El nombre no puede ser nulo o vacío");
        if(apellido == null || apellido.isBlank())
            throw new IllegalAccessException("El apellido no puede ser nulo o vacío");
        if(email == null || EmailValidate.isValid(email))
            throw new IllegalAccessException("Correo electronico inválido");
        if(salarioBase == null || salarioBase.compareTo(BigDecimal.ZERO) >= 0 && salarioBase.compareTo(new BigDecimal("15000000")) <= 0)
            throw new IllegalAccessException("El salario base debe ser mayor a cero y menor a 15000000");
    }
}
