package com.learn.mentee.util.exporter.impl;

import com.learn.mentee.entity.Ticket;
import com.learn.mentee.util.exporter.Exporter;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.OutputStream;
import java.util.List;

@Component
public class TicketPDFExporter implements Exporter<List<Ticket>> {
    @Override
    public void export(List<Ticket> tickets, OutputStream outputStream) {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, outputStream);

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(18);
        font.setColor(Color.PINK);

        Paragraph p = new Paragraph("List of Tickets", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, tickets);

        document.add(table);

        document.close();
    }

    private void writeTableData(PdfPTable table, List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            table.addCell(String.valueOf(ticket.getId()));
            table.addCell(String.valueOf(ticket.getEventId()));
            table.addCell(String.valueOf(ticket.getUserId()));
            table.addCell(String.valueOf(ticket.getTicketCategory()));
            table.addCell(String.valueOf(ticket.getPlace()));
        }
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        cell.setPhrase(new Phrase("Id", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Event id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("User id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ticket category", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Place", font));
        table.addCell(cell);
    }
}
