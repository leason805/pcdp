package com.boxun.pcdp.viewer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxun.estms.util.FreeMarkertUtil;
import com.boxun.estms.util.StringUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class EstimatePdfView extends AbstractIText5PdfView{

	private String templates;
	private String outputs;
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String html = outputs+ System.getProperty("file.separator") + StringUtil.uuid() + ".html";
		
		FreeMarkertUtil.initConfig(templates);
		
		Writer out = new OutputStreamWriter(new FileOutputStream(html),"UTF-8");  
		
		FreeMarkertUtil.processTemplate("des_report.flt", model, out);
		
		InputStreamReader isr = new InputStreamReader(new FileInputStream(html), "UTF-8");
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, isr);
	}

	public String getTemplates() {
		return templates;
	}

	public void setTemplates(String templates) {
		this.templates = templates;
	}

	public String getOutputs() {
		return outputs;
	}

	public void setOutputs(String outputs) {
		this.outputs = outputs;
	}

}
