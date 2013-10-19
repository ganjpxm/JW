/**
 * $Id: PdfUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfUtil {
	private static Logger log = LoggerFactory.getLogger(PdfUtil.class);
	
	public static PdfPCell addTitleCell(PdfPTable table, String value, int border, int colspan) {
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
		Paragraph paragraph = new Paragraph(value, font);
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setColspan(colspan);
		cell.setBorder(border);
		cell.setHorizontalAlignment(1);
		table.addCell(cell);
		return cell;
	}
	
	public static PdfPCell addImageCell(PdfPTable table, String imagePath, int border, int colspan) {
		PdfPCell cell = new PdfPCell();
		try {
			Image logo = Image.getInstance(imagePath);  
			cell.setImage(logo);
			cell.setBorder(border);
			cell.setColspan(colspan);
			table.addCell(cell);
		} catch (Exception e) {
			log.error(imagePath + " read fail : " + e.getMessage());
		}
		return cell;
	}
	
	public static void addHeadCell(PdfPTable table, String value, int colspan) {
		addCell(table, value, 14, Font.BOLD, BaseColor.BLACK, 1, colspan, Element.ALIGN_MIDDLE,
				Element.ALIGN_CENTER, 4, 8, 0, 0);
	}
	
	public static void addStringCell(PdfPTable table, String value, int colspan) {
		addCell(table, value, 12, Font.NORMAL, BaseColor.BLACK, 1, colspan, Element.ALIGN_BASELINE, 
				Element.ALIGN_LEFT, 4, 10, 10, 0);
	}
	
	public static void addStringCell(PdfPTable table, String value, int colspan, int hAlign) {
		addCell(table, value, 12, Font.NORMAL, BaseColor.BLACK, 1, colspan, Element.ALIGN_BASELINE, 
				hAlign, 4, 10, 10, 0);
	}
	
	public static void addNumberCell(PdfPTable table, String value, int colspan) {
		addCell(table, value, 12, Font.NORMAL, BaseColor.BLACK, 1, colspan, Element.ALIGN_BASELINE, 
				Element.ALIGN_RIGHT, 4, 10, 0, 10);
	}
	
	public static void addLableCellWithoutBorder(PdfPTable table, String value, int colspan) {
		addCell(table, value, 12, Font.BOLD, BaseColor.BLACK, 0, colspan, Element.ALIGN_BASELINE, 
				Element.ALIGN_LEFT, 20, 0, 0, 0);
	}
	
	public static void addValueCellWithoutBorder(PdfPTable table, String value, int colspan) {
		addCell(table, value, 12, Font.NORMAL, BaseColor.BLACK, 0, colspan, Element.ALIGN_BASELINE,
				Element.ALIGN_LEFT, 20, 0, 0, 0);
	}
	
	public static void addCell(PdfPTable table, String value, float fontSize, int fontStyle, BaseColor fontColor, 
			int borderWidth, int colspan, int vAlign, int hAlign, int padTop, int padBottom, int padLeft, int padRight){
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, fontSize, fontStyle, fontColor);
		Paragraph paragraph = new Paragraph(value, font);
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setColspan(colspan);
		cell.setBorderWidth(borderWidth);
		cell.setVerticalAlignment(vAlign);
		cell.setHorizontalAlignment(hAlign);
		cell.setPaddingTop(padTop);
		cell.setPaddingBottom(padBottom);
		cell.setPaddingLeft(padLeft);
		cell.setPaddingRight(padRight);
		table.addCell(cell);
	}
}

