package knjiznica.model;

import java.util.HashMap;
import java.util.Map;

public class ViewProvider {
	
	static Map<String, Object> views;
	
	static {
		views = new HashMap<String, Object>();
	}
	
	public static void setView(String name, Object object) {
		views.put(name, object);
	}
	 
	public static Object getView(String name) {
		return views.get(name); 
	}
}
