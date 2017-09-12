package knjiznica.model;

import java.util.ArrayList;

public class BusinessHours {
	
	private int libraryID;

	private ArrayList<String> check;
	private ArrayList<String> beginTime;
	private ArrayList<String> endTime;
	
	public BusinessHours(int libraryID, ArrayList<String> check,ArrayList<String> beginTime, ArrayList<String> endTime) {
		this.libraryID = libraryID;
		this.check = check;
		this.beginTime = beginTime;
		this.endTime = endTime;
		
	}

	public int getLibraryID() {
		return libraryID;
	}

	public void setLibraryID(int libraryID) {
		this.libraryID = libraryID;
	}

	public ArrayList<String> getCheck() {
		return check;
	}

	public void setCheck(ArrayList<String> check) {
		this.check = check;
	}

	public ArrayList<String> getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(ArrayList<String> beginTime) {
		this.beginTime = beginTime;
	}

	public ArrayList<String> getEndTime() {
		return endTime;
	}

	public void setEndTime(ArrayList<String> endTime) {
		this.endTime = endTime;
	}
	
}
