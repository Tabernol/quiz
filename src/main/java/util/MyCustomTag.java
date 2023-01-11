package util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.taglibs.standard.tag.common.sql.UpdateTagSupport;

import java.util.Calendar;

public class MyCustomTag extends UpdateTagSupport {

    private Integer countsOfVisit = 0;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();//returns the instance of JspWriter
        countsOfVisit++;
        try {
            out.print("How many people visit this page " + countsOfVisit);//printing date and time using JspWriter
        } catch (Exception e) {
            System.out.println(e);
        }
        return SKIP_BODY;//will not evaluate the body content of the tag
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
