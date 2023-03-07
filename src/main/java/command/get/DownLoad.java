package command.get;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import controllers.servlet.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class DownLoad implements RequestHandler {

    private static Logger logger = LogManager.getLogger(DownLoad.class);

    private final int ARBITARY_SIZE = 1048;

    /**
     * This method takes user`s id from request or from session.
     * It creates pdf file with completed tests(quizs) by user Id
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/pdf");
        MyPdfWriter myPdfWriter = new MyPdfWriter();

        Long id;
        String userId = req.getParameter("user_id");
        if (userId != null) {
            id = Long.valueOf(userId);
            logger.info("Admin downloads result user with id " + id);
        } else {
            id = (Long) req.getSession().getAttribute("user_id");
            logger.info("User downloads result user with id " + id);
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
            logger.warn("Trouble with downloading result with ID " + id, de);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
