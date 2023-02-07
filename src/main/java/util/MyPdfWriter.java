package util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dto.ResultDto;
import exeptions.DataBaseException;
import repo.ResultRepo;
import servises.ResultService;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class MyPdfWriter {
    ResultService resultService;

    public PdfPTable getPdfTable(Long userId) {
        PdfPTable table = new PdfPTable(5);
        table = addTableHeader(table);
        table = addRows(table, userId);
        return table;
    }

    private PdfPTable addTableHeader(PdfPTable table) {
        Stream.of("Test name", "Subject", "Difficult", "Duration", "Grade")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        return table;
    }

    private PdfPTable addRows(PdfPTable table, Long userId) {
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
        return table;
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
