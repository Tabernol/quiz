package command.get;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import command.AbstractCommand;
import controllers.AppContext;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import util.pdfWriter.MyPdfWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Download.class is allowed for admin and student
 * The purpose of this class is download pdf file with user`s result
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class DownLoad extends AbstractCommand {

    /**
     * This method takes user`s id from request or from session.
     * It creates pdf file with completed tests(quizs) by user Id
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/pdf");
        MyPdfWriter myPdfWriter = AppContext.getInstance().getMyPdfWriter();

        Long id;
        String userId = req.getParameter(USER_ID);
        if (userId != null) {
            id = Long.valueOf(userId);
            log.info("Admin downloads result user with id " + id);
        } else {
            id = (Long) req.getSession().getAttribute("user_id");
            log.info("User downloads result user with id " + id);
        }


        try {
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter.getInstance(document, resp.getOutputStream());
            // step 3
            document.open();
            // step 4
            document.add(myPdfWriter.getPdfTable(id));
            // step 5
            document.close();
        } catch (DocumentException de) {
            log.warn("Trouble with downloading result with ID " + id, de);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        } catch (DataBaseException e) {
            log.warn("problem with database", e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
