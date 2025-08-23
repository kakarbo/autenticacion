package co.com.crediya.model.user.service;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class EmailValidate {
    public static boolean isValid(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+@(?:[a-zA-Z0-9]+\\.)+[a-zA-Z]{2,7}$";

        Pattern p = Pattern.compile(emailRegex);

        return email != null && p.matcher(email).matches();
    }
}
