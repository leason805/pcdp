package com.boxun.estms.util;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {

	public static final String project = "performance";
	
	public static final String name = "IndicatorScore";
	public static final String model = "PIndicatorScore";
	public static final String modelName = "indicatorscore";
	
	public static final String template_path = "G:\\project\\FoFu\\pcdp\\src\\main\\webapp\\template\\";
	
	public static final String path  = "G:\\project\\FoFu\\pcdp\\src\\main\\java\\com\\boxun\\pcdp\\";
	public static final String jsp_path  = "G:\\project\\FoFu\\pcdp\\src\\main\\webapp\\WEB-INF\\views\\pcdp\\";
	
	public static final String controller_path  = "knowledge\\controller\\";
	public static final String model_path  = "\\app\\models\\exam\\";
	public static final String service_path  = "\\app\\service\\exam\\";
	public static final String html_path  = "\\app\\views\\exam\\";
	
	public static final String template = "\\public\\template\\";
	public static final String controller_template = "ProjectController.java";
	public static final String model_template = "KProject.java";
	public static final String service_template = "IProjectService.java";
	public static final String service_impl_template = "ProjectServiceImpl.java";
	public static final String dao_template = "ProjectDaoImpl.java";
	public static final String index_template = "list.jsp";
	public static final String show_template = "show.jsp";
	
	public static final String index_html_template = "list.jsp";
	public static final String detail_html_template = "show.jsp";
	
	public static String model_folder = "";
	
	public static String controller_folder = "";
	public static String dao_folder = "";
	public static String service_folder = "";
	public static String service_impl_folder = "";
	public static String jsp_folder = "";
	
	
	public static void main(String[] args) {
		genFolders();
		
		genController();
		genModel();
		genDao();
		genService();
		genServiceImpl();
//		
		genIndexHTML();
		genDetailHTML();
	}
	
	public static void genFolders(){
		File file = new File(path+project);
		if(!file.exists())
			file.mkdir();
		
		model_folder = path+project+"\\entity\\";
		file = new File(model_folder);
		if(!file.exists())
			file.mkdir();
		
		controller_folder = path+project+"\\controller\\";
		file = new File(controller_folder);
		if(!file.exists())
			file.mkdir();
		
		dao_folder = path+project+"\\dao\\";
		file = new File(dao_folder);
		if(!file.exists())
			file.mkdir();
		dao_folder = path+project+"\\dao\\impl\\";
		file = new File(dao_folder);
		if(!file.exists())
			file.mkdir();
		
		service_folder = path+project+"\\service\\";
		file = new File(service_folder);
		if(!file.exists())
			file.mkdir();
		
		service_impl_folder = path+project+"\\service\\impl\\";
		file = new File(service_impl_folder);
		if(!file.exists())
			file.mkdir();
		
		jsp_folder = jsp_path+project+"\\";
		file = new File(jsp_folder);
		if(!file.exists())
			file.mkdir();
		
		jsp_folder = jsp_folder + modelName + "\\";
		file = new File(jsp_folder);
		if(!file.exists())
			file.mkdir();

	}
	
	public static void genController(){
		List<String> lines = FileUtil.readFileWithoutTrim(template_path+controller_template);
		List<String> outputLines = new ArrayList<String>();
		for(String line : lines){
			outputLines.add(line.replaceAll("knowledge", project).replaceAll("KProject", model).replaceAll("Project", name).replaceAll("project", modelName));
		}
		FileUtil.writeFile(controller_folder+name+"Controller.java", outputLines);
	}
	
	public static void genDao(){
		List<String> lines = FileUtil.readFileWithoutTrim(template_path+dao_template);
		List<String> outputLines = new ArrayList<String>();
		for(String line : lines){
			outputLines.add(line.replaceAll("knowledge", project).replaceAll("KProject", model).replaceAll("Project", name).replaceAll("project", modelName));
		}
		FileUtil.writeFile(dao_folder +name+"DaoImpl.java", outputLines);
	}
	
	public static void genService(){
		List<String> lines = FileUtil.readFileWithoutTrim(template_path+service_template);
		List<String> outputLines = new ArrayList<String>();
		for(String line : lines){
			outputLines.add(line.replaceAll("knowledge", project).replaceAll("KProject", model).replaceAll("Project", name).replaceAll("project", modelName));
		}
		FileUtil.writeFile(service_folder +"I" +name+"Service.java", outputLines);
	}
	
	public static void genServiceImpl(){
		List<String> lines = FileUtil.readFileWithoutTrim(template_path+service_impl_template);
		List<String> outputLines = new ArrayList<String>();
		for(String line : lines){
			outputLines.add(line.replaceAll("knowledge", project).replaceAll("KProject", model).replaceAll("Project", name).replaceAll("project", modelName));
		}
		FileUtil.writeFile(service_impl_folder+name+"ServiceImpl.java", outputLines);
	}
	
	public static void genModel(){
		List<String> lines = FileUtil.readFileWithoutTrim(template_path+model_template);
		List<String> outputLines = new ArrayList<String>();
		for(String line : lines){
			outputLines.add(line.replaceAll("knowledge", project).replaceAll("KProject", model).replaceAll("Project", name).replaceAll("project", modelName));
		}
		FileUtil.writeFile(model_folder+model+".java", outputLines);
	}
	
	
	
	public static void genIndexHTML(){
		List<String> lines = FileUtil.readFileWithoutTrim(template_path+index_html_template);
		List<String> outputLines = new ArrayList<String>();
		for(String line : lines){
			outputLines.add(line);
		}

		FileUtil.writeFile(jsp_folder+"list.jsp", outputLines);
	}
	
	public static void genDetailHTML(){
		List<String> lines = FileUtil.readFileWithoutTrim(template_path+detail_html_template);
		List<String> outputLines = new ArrayList<String>();
		for(String line : lines){
			outputLines.add(line);
		}

		FileUtil.writeFile(jsp_folder+"show.jsp", outputLines);
	}
}
