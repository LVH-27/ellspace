package knjiznica.model;

public class FormattingName {
	
	private static String newName = "";
	
	public static String format(String name) {
		
		newName = "";
		
		String[] nameList = name.split("-");
		
		for (int i = 0; i < nameList.length; ++i) {
			
			nameList[i] = nameList[i].trim();
			newName += nameList[i].substring(0, 1).toUpperCase() + nameList[i].substring(1);
			if (i < nameList.length - 1) {
				newName += "-";
			}	
		}
		
		nameList = newName.split(" ");
		newName = "";
		
		for (int i = 0; i < nameList.length; ++i) {
			newName += nameList[i].substring(0, 1).toUpperCase() + nameList[i].substring(1);
			
			if (i < nameList.length - 1) {
				newName += " ";
			}
		}
		
		return newName;
	}
}
