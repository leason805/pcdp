package com.boxun.estms.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	
	public static String readFiles(String file){
		StringBuilder sb = new StringBuilder();
		File f = new File(file);
		if(f.exists()){
			List<String> list = readFileWithoutTrim(f);
			for(String str : list){
				sb.append(str);
			}
		}
		return sb.toString();
	}

	/**
	 * 鎶婃枃浠惰鍙栬繘list, 涓嶅瀛楃涓茶繘琛宼rim
	 * @param file
	 * @return
	 */
	public static List<String> readFileWithoutTrim(String file){
		File f = new File(file);
		if(f.exists()){
			return readFileWithoutTrim(f);
		}
		return null;
	}
	
	/**
	 * 鎶婃枃浠惰鍙栬繘list, 涓嶅瀛楃涓茶繘琛宼rim
	 * @param file
	 * @return
	 */
	public static List<String> readFileWithoutTrim(File file){
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                list.add(tempString);
            }
            reader.close();
		}
		catch(Exception e){
			list = null;
		}
		finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
		}
		return list;
	}
	
	/**
	 * 鎶婃枃浠惰鍙栬繘list, 鍚屾椂瀵瑰瓧绗︿覆杩涜trim
	 * @param file
	 * @return
	 */
	public static List<String> readFileWithTrim(String file){
		File f = new File(file);
		if(f.exists()){
			return readFileWithTrim(f);
		}
		return null;
	}
	
	/**
	 * 鎶婃枃浠惰鍙栬繘list, 鍚屾椂瀵瑰瓧绗︿覆杩涜trim
	 * @param file
	 * @return
	 */
	public static List<String> readFileWithTrim(File file){
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                list.add(StringUtil.trim(tempString));
            }
            reader.close();
		}
		catch(Exception e){
			list = null;
		}
		finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
		}
		return list;
	}
	
	/**
	 * 鍐欐枃浠�
	 * @param file
	 * @param lines
	 * @return
	 */
	public static boolean writeFile(String file, List<String> lines){
		BufferedWriter writer = null;
		boolean success = false;
		try{
			writer = new BufferedWriter(new FileWriter(file));
            for(String line : lines){
            	writer.write(line + "\n");
            }
            writer.close();
            success = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
            if (writer != null) {
                try {
                	writer.close();
                } catch (IOException e1) {
                	e1.printStackTrace();
                }
            }
		}
		return success;
	}
	
	public static boolean createDir(String dir){
		boolean result = false;
		
		try{
			File file = new File(dir);
			if(!file.exists())
				file.mkdir();
			result = true;
		}
		catch(Exception e){
			
		}
		return result;
	}
}
