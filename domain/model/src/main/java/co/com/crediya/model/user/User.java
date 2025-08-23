package co.com.crediya.model.user;
import co.com.crediya.model.user.service.EmailValidate;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private UUID idUser;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private BigDecimal salarioBase;

    public void validateData() throws IllegalAccessException {
        if(nombre == null || nombre.isBlank())
            throw new IllegalAccessException("El nombre no puede ser nulo o vacío");
        if(apellido == null || apellido.isBlank())
            throw new IllegalAccessException("El apellido no puede ser nulo o vacío");
        if(correoElectronico == null || EmailValidate.isValid(correoElectronico))
            throw new IllegalAccessException("Correo electronico inválido");
        if(salarioBase == null || salarioBase.compareTo(BigDecimal.ZERO) >= 0 && salarioBase.compareTo(new BigDecimal("15000000")) <= 0)
            throw new IllegalAccessException("El salario base debe ser mayor a cero y menor a 15000000");
    }
}
