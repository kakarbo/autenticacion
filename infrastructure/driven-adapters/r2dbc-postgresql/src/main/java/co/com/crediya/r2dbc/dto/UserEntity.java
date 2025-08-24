package co.com.crediya.r2dbc.dto;

import co.com.crediya.model.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Table("usuario")
public class UserEntity {
    @Id
    private UUID idUser;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private BigDecimal salarioBase;

    public static UserEntity fromDomain(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.idUser = UUID.fromString(user.getIdUser() != null ? user.getIdUser() : UUID.randomUUID().toString());
        userEntity.nombre = user.getNombre();
        userEntity.apellido = user.getApellido();
        userEntity.fechaNacimiento = user.getFechaNacimiento();
        userEntity.direccion = user.getDireccion();
        userEntity.telefono = user.getTelefono();
        userEntity.correoElectronico = user.getTelefono();
        userEntity.salarioBase = user.getSalarioBase();
        return userEntity;
    }

    public User toDomain() {
        return User.builder().build();
    }
}
