
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import validator.DataValidator;

public class ValidatorTest {

    DataValidator dataValidator;
    @BeforeEach
    public void setUp(){
        dataValidator = new DataValidator();
    }

    @Test
    public void difficultValidatorTest() {
        Assertions.assertEquals(false, dataValidator.validateDifficult(-1));
        Assertions.assertEquals(false, dataValidator.validateDifficult(101));
        Assertions.assertEquals(false, dataValidator.validateDifficult(0));
        Assertions.assertEquals(true, dataValidator.validateDifficult(100));
        Assertions.assertEquals(true, dataValidator.validateDifficult(1));
        Assertions.assertEquals(true, dataValidator.validateDifficult(14));
    }

    @Test
    public void durationValidatorTest() {
        Assertions.assertEquals(false, dataValidator.validateDuration(-1));
        Assertions.assertEquals(false, dataValidator.validateDuration(31));
        Assertions.assertEquals(true, dataValidator.validateDuration(5));
        Assertions.assertEquals(true, dataValidator.validateDuration(10));
        Assertions.assertEquals(true, dataValidator.validateDuration(30));
    }

    @Test
    public void validateLengthString() {
        Assertions.assertEquals(true, dataValidator.validateForNotLongString("12345678901234567890"));
        Assertions.assertEquals(false, dataValidator.validateForNotLongString("123456789012345678901"));
        Assertions.assertEquals(true, dataValidator.validateForNotLongString("qwertyuiopasdfghjkl;"));
        Assertions.assertEquals(true, dataValidator.validateForNotLongString("  23  ./dfg  sdf_"));
        Assertions.assertEquals(true, dataValidator.validateForNotLongString("./!@#$%^&*()  fd"));
        Assertions.assertEquals(true, dataValidator.validateForNotLongString("q"));
        Assertions.assertEquals(false, dataValidator.validateForNotLongString(""));
    }

    @Test
    public void validateLoginTest() {
        Assertions.assertEquals(true, dataValidator.validateLogin("zdfg@dfgh.cv"));
        Assertions.assertEquals(true, dataValidator.validateLogin("zd_fg@dfgh.cv"));
        Assertions.assertEquals(true, dataValidator.validateLogin("zd-fg@dfgh.cv"));
        Assertions.assertEquals(true, dataValidator.validateLogin("zd-fg@dfgh.cvfgh"));
        Assertions.assertEquals(true, dataValidator.validateLogin("zd-fg@d123fgh.cvfgh"));
        Assertions.assertEquals(true, dataValidator.validateLogin("zd-fg@d123fg23h.cvfgh"));
        Assertions.assertEquals(true, dataValidator.validateLogin("zd-fg@d123fg23h.cvfgh"));
        Assertions.assertEquals(true, dataValidator.validateLogin("zd-fg@d123fg2-3h.cvfgh"));
        Assertions.assertEquals(true, dataValidator.validateLogin("zy@dy.cv"));
        Assertions.assertEquals(false, dataValidator.validateLogin("zd-fg@d123fg2_3h.cvfgh"));
        Assertions.assertEquals(false, dataValidator.validateLogin("zd-fg@d123fg23h.t_"));
        Assertions.assertEquals(false, dataValidator.validateLogin("zd-fg@d123fg23h.t-h"));
        Assertions.assertEquals(false, dataValidator.validateLogin("zd-fg@d123fg23h.cvf_gh"));
        Assertions.assertEquals(false, dataValidator.validateLogin("zd-fg@d123fg23h.c8gh"));
        Assertions.assertEquals(false, dataValidator.validateLogin("zd-fg@d123fg23h.c"));
    }

    @Test
    public void validateForNameTest() {
        Assertions.assertEquals(true, dataValidator.validateForName("ad ads asd asd asd"));
        Assertions.assertEquals(true, dataValidator.validateForName("ad"));
        Assertions.assertEquals(true, dataValidator.validateForName("a "));
        Assertions.assertEquals(true, dataValidator.validateForName("qwertyuiopasdfghjklz"));
        Assertions.assertEquals(false, dataValidator.validateForName("qwertyuiopasdfghjklzx"));
        Assertions.assertEquals(false, dataValidator.validateForName("sdfs12"));
        Assertions.assertEquals(false, dataValidator.validateForName("123"));
        Assertions.assertEquals(false, dataValidator.validateForName("dsfgsdf_dfgd"));
        Assertions.assertEquals(false, dataValidator.validateForName("dsfgsdf-sdf"));
        Assertions.assertEquals(true, dataValidator.validateForName("ава  пвп вм  см"));
        Assertions.assertEquals(true, dataValidator.validateForName("йцукенгшщзфтвапролдя"));
        Assertions.assertEquals(false, dataValidator.validateForName("йцукенгшщзфтвапролдяь"));
    }

    @Test
    public void validateForNamePlusNumber() {
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("ad ads asd asd asd"));
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("ad"));
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("a "));
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("qwertyuiopasdfghjklz"));
        Assertions.assertEquals(false, dataValidator.validateForNamePlusNumber("qwertyuiopasdfghjklzx"));
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("ddfh12"));
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("123"));
        Assertions.assertEquals(false, dataValidator.validateForNamePlusNumber("dsfgsdf_dfgd"));
        Assertions.assertEquals(false, dataValidator.validateForNamePlusNumber("dsfgsdf-sdf"));
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("авапвптвтвмсм"));
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("ава  пвптвт  вм  см"));
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("ава  пвптв24335вм см"));
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("йцукенгшщзфтвапролдя"));
        Assertions.assertEquals(false, dataValidator.validateForNamePlusNumber("йцукенгшщзфтвапролдяь"));
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("12"));
        Assertions.assertEquals(false, dataValidator.validateForNamePlusNumber("1"));
        Assertions.assertEquals(false, dataValidator.validateForNamePlusNumber("p"));
        Assertions.assertEquals(true, dataValidator.validateForNamePlusNumber("  "));
    }

    @Test
    public void validatePasswordTest() {
        Assertions.assertEquals(true, dataValidator.validatePassword("1234"));
        Assertions.assertEquals(true, dataValidator.validatePassword("qwwe"));
        Assertions.assertEquals(true, dataValidator.validatePassword("FHkkfg"));
        Assertions.assertEquals(true, dataValidator.validatePassword("!@#$%^BN"));
        Assertions.assertEquals(true, dataValidator.validatePassword("___  _"));
        Assertions.assertEquals(true, dataValidator.validatePassword("12ssdf_"));
        Assertions.assertEquals(true, dataValidator.validatePassword("2митсчf_"));
        Assertions.assertEquals(false, dataValidator.validatePassword("qwe"));
        Assertions.assertEquals(false, dataValidator.validatePassword("qe"));
        Assertions.assertEquals(false, dataValidator.validatePassword("укк"));
        Assertions.assertEquals(false, dataValidator.validatePassword("qwertyuiopa"));
        Assertions.assertEquals(false, dataValidator.validatePassword("qwertyu 2pa"));
    }

    @Test
    public void validateAvailabilityRoleTest(){

        Assertions.assertEquals(true, dataValidator.validateAvailabilityRole("admin"));
        Assertions.assertEquals(true, dataValidator.validateAvailabilityRole("student"));

    }
}
