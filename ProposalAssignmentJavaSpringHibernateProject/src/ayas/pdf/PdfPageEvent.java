/*
 * PdfPageEnd.java
 *
 * Created on February 25, 2008, 4:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package ayas.pdf;

import com.lowagie.text.pdf.*;
import com.lowagie.text.*;
import java.awt.Color;

/**
 *
 * @author 
 */
public class PdfPageEvent extends PdfPageEventHelper {

    private BaseFont baseFont;
    private int pdfType;
    private String waterMark = "AYAS";
    private int panelistCount;

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        Font fn = new Font(getBaseFont(), 8);
        Font fnLarge = new Font(getBaseFont(), 8, Font.BOLD);


        Paragraph paragraph = new Paragraph(document.getPageNumber() + "", fn);
        paragraph.setAlignment(Element.ALIGN_RIGHT);

        float[] widths = {1f, 1f};
        PdfPTable footer = new PdfPTable(widths);

        PdfPCell cell = new PdfPCell();
        cell.setBorderWidth(0);
        cell.addElement(paragraph);
        footer.addCell(cell);

        footer.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
        footer.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());

        PdfContentByte cb = writer.getDirectContent();
        PdfGState pgs = new PdfGState();
        pgs.setFillOpacity(0.2f);
        cb.saveState();
        cb.setGState(pgs);
        cb.setColorFill(Color.GRAY);
        cb.beginText();
        cb.setFontAndSize(fn.getBaseFont(), 48);
        cb.showTextAligned(Element.ALIGN_CENTER, getWaterMark(), document.getPageSize().getWidth() / 2, document.getPageSize().getHeight() / 2, 45);
        cb.endText();
        cb.restoreState();

    }

    public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvas) {
        float x1 = position.getLeft() + 2;
        float x2 = position.getRight() - 2;
        float y1 = position.getTop() - 2;
        float y2 = position.getBottom() + 2;
        PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
        cb.setRGBColorStroke(0xFF, 0x00, 0x00);
        cb.rectangle(x1, y1, x2 - x1, y2 - y1);
        cb.stroke();
        cb.resetRGBColorStroke();
    }

    public int getPdfType() {
        return pdfType;
    }

    public void setPdfType(int pdfType) {
        this.pdfType = pdfType;
    }

    public BaseFont getBaseFont() {
        return baseFont;
    }

    public void setBaseFont(BaseFont baseFont) {
        this.baseFont = baseFont;
    }

    public String getWaterMark() {
        return waterMark;
    }

    public void setWaterMark(String waterMark) {
        this.waterMark = waterMark;
    }

    public int getPanelistCount() {
        return panelistCount;
    }

    public void setPanelistCount(int panelistCount) {
        this.panelistCount = panelistCount;
    }
}


