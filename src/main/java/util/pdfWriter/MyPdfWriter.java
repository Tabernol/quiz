package util.pdfWriter;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dto.ResultDto;
import exeptions.DataBaseException;
import repo.impl.ResultRepoImpl;
import servises.ResultService;

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
        resultService = new ResultService(new ResultRepoImpl());
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
}
