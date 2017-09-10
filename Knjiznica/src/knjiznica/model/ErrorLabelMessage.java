package knjiznica.model;

public class ErrorLabelMessage {
	
	private static String infoMiss = "Missing some information."; 
	private static String wrongFormat = "Verify that you have entered the correct information.";
	private static String failReach = "Could not reach database!";
	private static String something = "Something went wrong!";

	public static String getInfoMiss() {
		return infoMiss;
	}

	public static String getWrongFormat() {
		return wrongFormat;
	}

	public static String getFailReach() {
		return failReach;
	}

	public static String getSomething() {
		return something;
	}
}
