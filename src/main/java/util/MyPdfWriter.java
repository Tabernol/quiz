package util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dto.ResultDto;
import exeptions.DataBaseException;
import repo.ResultRepo;
import servises.ResultService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class MyPdfWriter {
    ResultService resultService;

    public void createPdf(Long userId) {
        System.out.println("start creat");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream("src/main/webapp/WEB-INF/pdf/table" + userId + ".pdf"));
            System.out.println("before open");
            document.open();


            PdfPTable table = new PdfPTable(5);
            MyPdfWriter myPdfWriter = new MyPdfWriter();
            myPdfWriter.addTableHeader(table);
            myPdfWriter.addRows(table, userId);
            //  addCustomRows(table);
            document.add(table);
            System.out.println("Finish creat");
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            document.close();
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Test name", "Subject", "Difficult", "Duration", "Grade")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, Long userId) {
        resultService = new ResultService(new ResultRepo());
        List<ResultDto> allResultByUserId = null;
        try {
            allResultByUserId = resultService.getAllResultByUserId(userId);
        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        }
        for (ResultDto resultDto : allResultByUserId) {
            table.addCell(resultDto.getTestName());
            table.addCell(resultDto.getSubject());
            table.addCell(resultDto.getDifficult().toString());
            table.addCell(resultDto.getDuration().toString());
            table.addCell(resultDto.getGrade().toString());
        }
    }

//    private static void addCustomRows(PdfPTable table)
//            throws URISyntaxException, BadElementException, IOException {
//        //   Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
//        //  Image img = Image.getInstance(path.toAbsolutePath().toString());
//        //  img.scalePercent(10);
//
////        PdfPCell imageCell = new PdfPCell(img);
////        table.addCell(imageCell);
//
//        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
//        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(horizontalAlignCell);
//
//        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
//        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//        table.addCell(verticalAlignCell);
//    }


}
