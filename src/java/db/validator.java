package db;

import java.util.HashMap;
import java.util.Map;

public class validator {
	
	
	
	public static Map vld(String count,String type){
		Map<String, String> errorList = new HashMap<>();
		
		for(int i =0; i <count.length(); i++){
			if(Character.isLetter(count.charAt(i))){
				errorList.put("count", "Ainult numbrid");
				
			}
			
		}
		
		if(type.length()==0){
			errorList.put("type", "Ei tohi olla tuhi");
		}
		if(type.length()>10){
			errorList.put("type", "max 10 tahte");
			
		}
		return errorList;
		
	}

}
