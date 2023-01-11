import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import validator.DataValidator;

public class ValidatorTest {

    @Test
    public void difficultValidatorTest() {
        Assertions.assertEquals(false, DataValidator.validateDifficult(-1));
        Assertions.assertEquals(false, DataValidator.validateDifficult(101));
        Assertions.assertEquals(false, DataValidator.validateDifficult(0));
        Assertions.assertEquals(true, DataValidator.validateDifficult(100));
        Assertions.assertEquals(true, DataValidator.validateDifficult(1));
        Assertions.assertEquals(true, DataValidator.validateDifficult(14));
    }

    @Test
    public void durationValidatorTest() {
        Assertions.assertEquals(false, DataValidator.validateDuration(-1));
        Assertions.assertEquals(false, DataValidator.validateDuration(31));
        Assertions.assertEquals(true, DataValidator.validateDuration(5));
        Assertions.assertEquals(true, DataValidator.validateDuration(10));
        Assertions.assertEquals(true, DataValidator.validateDuration(30));
    }

    @Test
    public void validateLengthString() {
        Assertions.assertEquals(true, DataValidator.validateForNotLongString("12345678901234567890"));
        Assertions.assertEquals(false, DataValidator.validateForNotLongString("123456789012345678901"));
        Assertions.assertEquals(true, DataValidator.validateForNotLongString("qwertyuiopasdfghjkl;"));
        Assertions.assertEquals(true, DataValidator.validateForNotLongString("  23  ./dfg  sdf_"));
        Assertions.assertEquals(true, DataValidator.validateForNotLongString("./!@#$%^&*()  fd"));
        Assertions.assertEquals(true, DataValidator.validateForNotLongString("q"));
        Assertions.assertEquals(false, DataValidator.validateForNotLongString(""));
    }

    @Test
    public void validateLoginTest() {
        Assertions.assertEquals(true, DataValidator.validateLogin("zdfg@dfgh.cv"));
        Assertions.assertEquals(true, DataValidator.validateLogin("zd_fg@dfgh.cv"));
        Assertions.assertEquals(true, DataValidator.validateLogin("zd-fg@dfgh.cv"));
        Assertions.assertEquals(true, DataValidator.validateLogin("zd-fg@dfgh.cvfgh"));
        Assertions.assertEquals(true, DataValidator.validateLogin("zd-fg@d123fgh.cvfgh"));
        Assertions.assertEquals(true, DataValidator.validateLogin("zd-fg@d123fg23h.cvfgh"));
        Assertions.assertEquals(true, DataValidator.validateLogin("zd-fg@d123fg23h.cvfgh"));
        Assertions.assertEquals(true, DataValidator.validateLogin("zd-fg@d123fg2-3h.cvfgh"));
        Assertions.assertEquals(true, DataValidator.validateLogin("zy@dy.cv"));
        Assertions.assertEquals(false, DataValidator.validateLogin("zd-fg@d123fg2_3h.cvfgh"));
        Assertions.assertEquals(false, DataValidator.validateLogin("zd-fg@d123fg23h.t_"));
        Assertions.assertEquals(false, DataValidator.validateLogin("zd-fg@d123fg23h.t-h"));
        Assertions.assertEquals(false, DataValidator.validateLogin("zd-fg@d123fg23h.cvf_gh"));
        Assertions.assertEquals(false, DataValidator.validateLogin("zd-fg@d123fg23h.c8gh"));
        Assertions.assertEquals(false, DataValidator.validateLogin("zd-fg@d123fg23h.c"));
    }

    @Test
    public void validateForNameTest() {
        Assertions.assertEquals(true, DataValidator.validateForName("ad ads asd asd asd"));
        Assertions.assertEquals(true, DataValidator.validateForName("ad"));
        Assertions.assertEquals(true, DataValidator.validateForName("a "));
        Assertions.assertEquals(true, DataValidator.validateForName("qwertyuiopasdfghjklz"));
        Assertions.assertEquals(false, DataValidator.validateForName("qwertyuiopasdfghjklzx"));
        Assertions.assertEquals(false, DataValidator.validateForName("sdfs12"));
        Assertions.assertEquals(false, DataValidator.validateForName("123"));
        Assertions.assertEquals(false, DataValidator.validateForName("dsfgsdf_dfgd"));
        Assertions.assertEquals(false, DataValidator.validateForName("dsfgsdf-sdf"));
        Assertions.assertEquals(true, DataValidator.validateForName("авапвптвтвмсм"));
        Assertions.assertEquals(true, DataValidator.validateForName("ава  пвп вм  см"));
        Assertions.assertEquals(true, DataValidator.validateForName("йцукенгшщзфтвапролдя"));
        Assertions.assertEquals(false, DataValidator.validateForName("йцукенгшщзфтвапролдяь"));
    }

    @Test
    public void validateForNamePlusNumber() {
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("ad ads asd asd asd"));
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("ad"));
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("a "));
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("qwertyuiopasdfghjklz"));
        Assertions.assertEquals(false, DataValidator.validateForNamePlusNumber("qwertyuiopasdfghjklzx"));
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("ddfh12"));
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("123"));
        Assertions.assertEquals(false, DataValidator.validateForNamePlusNumber("dsfgsdf_dfgd"));
        Assertions.assertEquals(false, DataValidator.validateForNamePlusNumber("dsfgsdf-sdf"));
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("авапвптвтвмсм"));
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("ава  пвптвт  вм  см"));
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("ава  пвптв24335вм см"));
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("йцукенгшщзфтвапролдя"));
        Assertions.assertEquals(false, DataValidator.validateForNamePlusNumber("йцукенгшщзфтвапролдяь"));
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("12"));
        Assertions.assertEquals(false, DataValidator.validateForNamePlusNumber("1"));
        Assertions.assertEquals(false, DataValidator.validateForNamePlusNumber("p"));
        Assertions.assertEquals(true, DataValidator.validateForNamePlusNumber("  "));
    }

    @Test
    public void validatePasswordTest() {
        Assertions.assertEquals(true, DataValidator.validatePassword("1234"));
        Assertions.assertEquals(true, DataValidator.validatePassword("qwwe"));
        Assertions.assertEquals(true, DataValidator.validatePassword("FHkkfg"));
        Assertions.assertEquals(true, DataValidator.validatePassword("!@#$%^BN"));
        Assertions.assertEquals(true, DataValidator.validatePassword("___  _"));
        Assertions.assertEquals(true, DataValidator.validatePassword("12ssdf_"));
        Assertions.assertEquals(true, DataValidator.validatePassword("2митсчf_"));
        Assertions.assertEquals(false, DataValidator.validatePassword("qwe"));
        Assertions.assertEquals(false, DataValidator.validatePassword("qe"));
        Assertions.assertEquals(false, DataValidator.validatePassword("укк"));
        Assertions.assertEquals(false, DataValidator.validatePassword("qwertyuiopa"));
        Assertions.assertEquals(false, DataValidator.validatePassword("qwertyu 2pa"));
    }
}
