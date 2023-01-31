package service_test;

import org.junit.jupiter.api.Test;
import servises.PasswordHashingService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordHashingTest {

    PasswordHashingService passwordHashingService = new PasswordHashingService();

//    @Test
//    public void getSalt() throws NoSuchAlgorithmException {
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//        byte[] salt = new byte[16];
//        sr.nextBytes(salt);
//        assertArrayEquals(salt, passwordHashingService.);
//    }
    @Test
    public void simpleCheck() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String somePass = PasswordHashingService.generateStrongPasswordHash("somePass");
        assertTrue(PasswordHashingService.validatePassword("somePass", somePass));
    }
}
