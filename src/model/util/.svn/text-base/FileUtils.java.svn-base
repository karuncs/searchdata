

package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;


public final class FileUtils {
	
	public static PropertyUtil propertyUtil = new PropertyUtil();
	
	public static String SEARCH_RESULT_DIR = propertyUtil.getPropertyValue("search.result.dir");
	
	public static String SEARCH_SERVER_RESULT_DIR = propertyUtil.getPropertyValue("search.server.result.dir");	
	
	/**
	 * @return
	 */
	public static String getAvailableSearchResultDirectory(){
		String availableIndexDirectory =  "NO SEARCH RESULT DIR IS SET UP !!!";
		if (new File(SEARCH_RESULT_DIR).exists()) {
			availableIndexDirectory = SEARCH_RESULT_DIR;
		}else if (new File(SEARCH_SERVER_RESULT_DIR).exists()) {
			availableIndexDirectory = SEARCH_SERVER_RESULT_DIR;
		}
		
		if (SEARCH_RESULT_DIR.equals(SEARCH_SERVER_RESULT_DIR)) {
			new Exception("SEARCH_SERVER_RESULT_DIR equals to SEARCH_RESULT_DIR !");
		}
		return availableIndexDirectory;
	}		
	
	/**
	 * @param fileName
	 * @param content
	 */
	public static void saveFile(String fileName, String content, boolean deleteFile){
		try{
			if (deleteFile) {
				File file = new File(fileName);
				file.delete();
			}
			File file = new File(fileName);
		    FileWriter fstream = new FileWriter(file,true);
		    BufferedWriter out = new BufferedWriter(fstream);
		    out.write(content);
		    out.close();				
	    }catch (Exception e){
	      System.err.println("Error: " + e.getMessage());
	    }
	 }

	/**
	 * @param fileName
	 * @param content
	 */
	public static void saveFile(String fileName, String content){
		try{
			File file = new File(fileName);
		    FileWriter fstream = new FileWriter(file,true);
		    BufferedWriter out = new BufferedWriter(fstream);
		    out.write(content);
		    out.close();				
	    }catch (Exception e){
	      System.err.println("Error: " + e.getMessage());
	    }
	 }

	
	/**
	 * @param fileName
	 * @param content
	 */
	public static void eraseSavedConentFile(String fileName, String content){
		try{
			File file = new File(fileName);
			 boolean deleted = file.delete();
			 if (deleted) {
				 	file = new File(fileName);
				    FileWriter fstream = new FileWriter(file,true);
				    BufferedWriter out = new BufferedWriter(fstream);
				    out.write(content);
				    out.close();	
			}
	    }catch (Exception e){
	      System.err.println("Error: " + e.getMessage());
	    }
	 }	
	/**
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static String readFileAsString(String filename) 
	  throws IOException
	  {
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    String line;
	    StringBuilder sb = new StringBuilder();
	    while ((line = reader.readLine()) != null)
	    {
	      sb.append(line + "\n");
	    }
	    reader.close();
	    return sb.toString();
	  }
	
	
	
	/**
	 * @param text
	 * @param resultDirPath
	 * @param extension
	 */
	public static void writeApendResults(String text, String resultDirPath, String extension){
		FileWriter fstream;
		BufferedWriter out;
		try {
			if (!text.trim().equals("")) {
				fstream = new FileWriter(resultDirPath+"."+extension,true);
				out = new BufferedWriter(fstream);
				out.newLine();
				out.write(text);
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}	
	
	/**
	 * @param text
	 * @param resultDirPath
	 * @param extension
	 */
	public static void writeApendDatedResults(String text, String resultDirPath, String extension){
		Calendar now = Calendar.getInstance();
		FileWriter fstream;
		BufferedWriter out;
		try {
			if (!text.trim().equals("")) {
				fstream = new FileWriter(resultDirPath+"_"+now.get(Calendar.DATE)+"."+extension,true);
				out = new BufferedWriter(fstream);
				out.newLine();
				out.write(text);
				
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}	
	


	
	/**
	 * @param text
	 */
	public static void writeResults(String text, String resultDirPath){
		Calendar now = Calendar.getInstance();
		String date = now.get(Calendar.DATE)+"_"+now.get(Calendar.HOUR)+"_"+now.get(Calendar.MINUTE)+"_"+now.get(Calendar.SECOND);
		FileWriter fstream;
		try {
			if (!text.trim().equals("")) {
				fstream = new FileWriter(resultDirPath+"_"+date+".txt");
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(text);
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}		

}