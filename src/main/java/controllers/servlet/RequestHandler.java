package controllers.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Stream;

public interface RequestHandler {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
