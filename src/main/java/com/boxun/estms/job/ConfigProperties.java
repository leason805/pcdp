package com.boxun.estms.job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {

	@Value("${template.path}")
    private static String templatePath;
	
    @Value("${configProperties['template.file']}")
    private static String templateFile;

	public static String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		ConfigProperties.templatePath = templatePath;
	}

	public static String getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(String templateFile) {
		ConfigProperties.templateFile = templateFile;
	}
}
