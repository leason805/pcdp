package com.boxun.estms.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class PDFUtil {

	public static void main(String[] args) {
		String html = "G:\\project\\FoFu\\pcdp\\src\\main\\webapp\\test.html";
		String pdf = "G:\\project\\FoFu\\pcdp\\src\\main\\webapp\\test.pdf";
		
		try{
			Document document = new Document();
			PdfWriter pdfwriter = PdfWriter.getInstance(document,
					new FileOutputStream(pdf));
			pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
			document.open();
	 
			// html文件
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					html), "UTF-8");
			XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, isr);
			document.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//PDFUtil.generate(html, pdf);
	}
	
	public static void generate(String html, String pdf){
	    Document doc = new Document();
	    PdfWriter writer;
		try {
			writer = PdfWriter.getInstance(doc, new FileOutputStream(pdf));
			doc.open();
			String content = FileUtil.readFiles(html);
		    XMLWorkerHelper.getInstance().parseXHtml(writer, doc,new StringReader(content));
		    doc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
