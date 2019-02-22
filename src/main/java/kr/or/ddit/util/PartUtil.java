package kr.or.ddit.util;

public class PartUtil {
	public static String getFileNameFromPart(String contetDisposition){
		String[] splits = contetDisposition.split("; ");
		
		String filename =null;
		for(String split : splits){
			if(split.startsWith("filename=")){
				filename = split.substring(split.indexOf("\"")+1,split.lastIndexOf("\""));
			}
		}
		return filename;
	}
}
