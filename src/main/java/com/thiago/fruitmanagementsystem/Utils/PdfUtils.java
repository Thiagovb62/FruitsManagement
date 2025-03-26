package com.thiago.fruitmanagementsystem.Utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.thiago.fruitmanagementsystem.Model.FrutaVendaResponseDTO;
import com.thiago.fruitmanagementsystem.Model.HistoricoResponseDTO;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
public class PdfUtils {
    public static byte[] generatePdfStream(List<HistoricoResponseDTO> historicos) throws DocumentException {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Create table with 3 columns
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Set column widths
            float[] columnWidths = {1f, 1f, 3f};
            table.setWidths(columnWidths);

            // Add table headers
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            PdfPCell cell = new PdfPCell(new Phrase("ID", boldFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Valor Total", boldFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Frutas Vendidas", boldFont));
            table.addCell(cell);

            // Add data rows
            for (HistoricoResponseDTO historico : historicos) {
                table.addCell(historico.id().toString());
                table.addCell(historico.valorTotal().toString());
                StringBuilder frutasVendidas = new StringBuilder();
                for (FrutaVendaResponseDTO frutaVenda : historico.frutasVendidas()) {
                    frutasVendidas.append(frutaVenda.frutaVendida().getNome())
                            .append(" - ")
                            .append(frutaVenda.qtdEscolhida())
                            .append(" - ")
                            .append(frutaVenda.dataVenda().toString())
                            .append("\n");
                }
                table.addCell(frutasVendidas.toString());
            }

            document.add(table);
            document.close(); // Ensure the document is closed
            return outputStream.toByteArray();

    }

    public static void savePdfToFile(byte[] pdfBytes, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(pdfBytes);
        }
    }

}