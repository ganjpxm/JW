package org.ganjp.jpw.cm.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.view.DocumentPdfView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class CmVocabularyPdfView extends DocumentPdfView {

	@Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "inline; filename="+ new String("用户列表".getBytes(), "UTF-8"));
		List<Map<String,String>> categoryMaps = (List<Map<String,String>>)model.get("categoryMaps");
		int index = 1;
		BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,  "UniGB-UCS2-H" ,  false );  
        Font grayChinese =  new Font(bfChinese  ,  12 , Font.NORMAL, BaseColor.GRAY);
        Font blackChinese =  new Font(bfChinese  ,  12 , Font.NORMAL, BaseColor.BLACK);
        Font redChinese = new Font(bfChinese  ,  12 , Font.BOLD, BaseColor.RED);
        Font redEnglish = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.RED);
        String language = request.getParameter("language");
        String categoryName = StringUtil.toString(model.get("categoryName"));
        document.addTitle(categoryName);  
        document.addAuthor("Gan Jainping");  
        document.addSubject(categoryName);  
        document.addCreator("Gan Jainping");
		//vocabularyId,firstName,firstPhonogram,firstPronounceUrl,secondName,secondPhonogram,secondPronounceUrl,imageUrl,levelName,displayNo,description
        Paragraph p = new Paragraph(categoryName, new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(15f);  
        document.add(p);
        if (categoryMaps==null || categoryMaps.isEmpty()) {
			document.add(new Paragraph("no records"));
		} else {
	        for (Map<String, String> map : categoryMaps) {
				String firstName = StringUtil.toString(map.get("firstName"));
				String firstPhonogram = StringUtil.toString(map.get("firstPhonogram"));
				String firstMean = StringUtil.toString(map.get("firstMean"));
				String firstDescription = StringUtil.toString(map.get("firstDescription")).replace("<br/>", "");
				String secondName = StringUtil.toString(map.get("secondName"));
				String secondDescription = StringUtil.toString(map.get("secondDescription")).replace("<br/>", "");
				if (StringUtil.isEmpty(language)) {
					document.add(new Paragraph(index++ + "." + firstName + firstPhonogram, redEnglish));
					if (StringUtil.isNotEmpty(firstMean)) {
						document.add(new Paragraph("Traslation : " + firstMean));
					}
					document.add(new Paragraph("More Information : " + firstDescription));
					document.add(new Paragraph("翻译 ：" + secondName, grayChinese));
					document.add(new Paragraph("更多详细信息 ：" + secondDescription, grayChinese));
				} else if (language.equals("en")) {
					document.add(new Paragraph(index++ + "." + firstName + firstPhonogram, redEnglish));
					if (StringUtil.isNotEmpty(firstMean)) {
						document.add(new Paragraph("Traslation : " + firstMean));
					}
					document.add(new Paragraph("More Information : " + firstDescription));
				} else if (language.equals("zh")) {
					document.add(new Paragraph(index++ + "." + secondName, redChinese));
					document.add(new Paragraph("" + secondDescription, blackChinese));
				} else {
					document.add(new Paragraph("no records"));
				}
			}
		}
	}
}
