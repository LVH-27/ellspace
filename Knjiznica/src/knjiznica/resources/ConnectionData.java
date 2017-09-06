package knjiznica.resources;

public class ConnectionData {
	
	private static String link = "jdbc:postgresql://localhost:5432/LibraryDatabase";
	private static String username = null;
	private static String password = null;
	
	public static String getLink() { 
		return link;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static void setPassword(String password) {
		ConnectionData.password = password;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		ConnectionData.username = username;
	}
	
}
