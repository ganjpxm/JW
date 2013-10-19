package org.ganjp.jpw.tm.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.jpw.core.util.PdfUtil;
import org.ganjp.jpw.core.web.view.DocumentPdfView;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TmTypePdfView extends DocumentPdfView {

	@Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "inline; filename="+ new String("用户列表".getBytes(), "UTF-8"));
		
		List<Map<String,String>> mapList = (List<Map<String,String>>) model.get("tmTypes");
		
		document.addTitle("TEST");  
		document.addAuthor("Gan Jianping");  
		document.addSubject("PDF TEST");  
		document.addKeywords("test");  
		document.addCreator("Gan Jianping");
		
		PdfPTable table1 = new PdfPTable(4);  
		table1.setWidthPercentage(90);
		table1.setSpacingBefore(100);
		
		PdfUtil.addLableCellWithoutBorder(table1, "USER NAME : ", 1);
		PdfUtil.addValueCellWithoutBorder(table1, "ganjp", 1);
		
		document.add(table1);
		
	}
	
}
