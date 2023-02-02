package util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MyPdfWriter {
    private static void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table) {
        table.addCell("row 1, col 1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

    private static void addCustomRows(PdfPTable table)
            throws URISyntaxException, BadElementException, IOException {
     //   Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
      //  Image img = Image.getInstance(path.toAbsolutePath().toString());
      //  img.scalePercent(10);

//        PdfPCell imageCell = new PdfPCell(img);
//        table.addCell(imageCell);

        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);

        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);
    }
    public static void main(String[] args) throws DocumentException, IOException, URISyntaxException {
//        Document document = new Document();
////
////        OutputStream outputStream =
////                new FileOutputStream(new File("D:\\TestFile.pdf"));
//
//            PdfWriter.getInstance(document,new
//
//                    FileOutputStream("iTextHelloWorld.pdf"));
//
//
//        document.open();
//        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
//        Chunk chunk = new Chunk("Hello World", font);
//
//        document.add(chunk);
//        document.close();

//        ===============
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("iTextTable.pdf"));

        document.open();

        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        addRows(table);
      //  addCustomRows(table);

        document.add(table);
        document.close();






//==================================================
        PdfReader pdfReader = new PdfReader("iTextHelloWorld.pdf");
        PdfStamper pdfStamper
                = new PdfStamper(pdfReader, new FileOutputStream("encryptedPdf.pdf"));


        pdfStamper.setEncryption(
                "userpass".getBytes(),
                "ownerpass".getBytes(),
                0,
                PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_COPY
        );

        pdfStamper.close();



    }

}
