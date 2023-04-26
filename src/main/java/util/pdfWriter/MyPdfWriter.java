package util.pdfWriter;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import context.AppContext;
import dto.ResultDto;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import servises.ResultService;

import java.util.List;
import java.util.stream.Stream;
@Slf4j
public class MyPdfWriter {
    private ResultService resultService;

    public MyPdfWriter(ResultService resultService) {
        this.resultService = resultService;
    }

    public PdfPTable getPdfTable(Long userId) throws DataBaseException {
        PdfPTable table = new PdfPTable(6);
        table = addTableHeader(table);
        table = addRows(table, userId);
        return table;
    }

    private PdfPTable addTableHeader(PdfPTable table) {
        Stream.of("Date","Test name", "Subject", "Difficult", "Duration", "Grade")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        return table;
    }

    private PdfPTable addRows(PdfPTable table, Long userId) throws DataBaseException {
        resultService = AppContext.getInstance().getResultService();
        List<ResultDto> allResultByUserId = null;
            allResultByUserId = resultService.getAllResultByUserId(userId);
        for (ResultDto resultDto : allResultByUserId) {
            table.addCell(resultDto.getDate().toString());
            table.addCell(resultDto.getTestName());
            table.addCell(resultDto.getSubject());
            table.addCell(resultDto.getDifficult().toString());
            table.addCell(resultDto.getDuration().toString());
            table.addCell(resultDto.getGrade().toString());
        }
        return table;
    }
}
