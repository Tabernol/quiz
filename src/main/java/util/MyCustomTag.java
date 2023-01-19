package util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.taglibs.standard.tag.common.sql.UpdateTagSupport;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Timer;

public class
MyCustomTag extends UpdateTagSupport {

    private Integer countsOfVisit = 0;
    LocalDateTime time = LocalDateTime.now();

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        countsOfVisit++;

        try {
            out.print(countsOfVisit);
        } catch (Exception e) {
            System.out.println(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
