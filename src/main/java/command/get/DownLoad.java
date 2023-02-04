package command.get;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controllers.servlet.RequestHandler;
import util.MyPdfWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

public class DownLoad implements RequestHandler {

//    public static void main(String[] args) {
//        File fileResult = new File("src/main/resources/pdf/table0.pdf");
//        try {
//            fileResult.createNewFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private final int ARBITARY_SIZE = 1048;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/pdf");
        MyPdfWriter myPdfWriter = new MyPdfWriter();
        Long id = (Long) req.getSession().getAttribute("user_id");
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
            throw new IOException(de.getMessage());
        }
    }
}
