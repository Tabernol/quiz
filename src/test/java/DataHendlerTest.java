import controllers.servlet.impl.ContentSupplierCommands;
import controllers.servlet.impl.DataHandleCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class DataHendlerTest {
    @Test
    public void simpleTest(){
        DataHandleCommand dataHandleCommand = new DataHandleCommand();
        ContentSupplierCommands contentSupplierCommands = new ContentSupplierCommands();
        Assertions.assertNotNull(dataHandleCommand);
    }
}
