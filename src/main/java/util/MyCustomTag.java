package util;

import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.jstl.fmt.LocaleSupport;

import app_listener.QuizAppListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.taglibs.standard.tag.common.sql.UpdateTagSupport;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
@Slf4j
public class MyCustomTag extends UpdateTagSupport {


    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        LocalDateTime time = LocalDateTime.now();
        String format = time.format(DateTimeFormatter.ISO_LOCAL_DATE);
        try {
            out.print(format);
        } catch (Exception e) {
       log.warn("Problem with custom tag", e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
