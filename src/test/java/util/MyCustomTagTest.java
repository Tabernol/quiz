package util;

import org.junit.jupiter.api.Test;
import util.MyCustomTag;

import static org.mockito.Mockito.*;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MyCustomTagTest {
    @Test
    public void testDoStartTag() throws JspException, IOException {

        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        MyCustomTag myCustomTag = new MyCustomTag();
        myCustomTag.setPageContext(pageContext);


        when(pageContext.getOut()).thenReturn(jspWriter);

        int result = myCustomTag.doStartTag();
        verify(jspWriter).print(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        assertEquals(SKIP_BODY, result);
    }
}
