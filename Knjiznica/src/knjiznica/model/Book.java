package knjiznica.model;

import java.sql.Date;

public class Book {
	
	private int ID;
	private String ISBN;
	private String title;
	private String summary;
	private int editionID;
	private int editionNumber;
	private String editionYear;
	private int editionNumberOfPages;
	private int ownerID;
	private int currentLocationID;
	private boolean available;
	private Date returnDate;
	private String information;
	
	public Book(int ID, String ISBN, String title, String summary, int editionID, int editionNumber, String editionYear, int editionNumberOfPages, int ownerID, int currentLocationID, boolean available, Date returnDate, String information) {
		this.ID = ID;
		this.ISBN = ISBN;
		this.title = title;
		this.summary = summary;
		this.editionID = editionID;
		this.editionNumber = editionNumber;
		this.editionYear = editionYear;
		this.editionNumberOfPages = editionNumberOfPages;
		this.ownerID = ownerID;
		this.currentLocationID = currentLocationID;
		this.available = available;
		this.returnDate = returnDate;
		this.information = information;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getEditionID() {
		return editionID;
	}

	public void setEditionID(int editionID) {
		this.editionID = editionID;
	}

	public int getEditionNumber() {
		return editionNumber;
	}

	public void setEditionNumber(int editionNumber) {
		this.editionNumber = editionNumber;
	}

	public String getEditionYear() {
		return editionYear;
	}

	public void setEditionYear(String editionYear) {
		this.editionYear = editionYear;
	}

	public int getEditionNumberOfPages() {
		return editionNumberOfPages;
	}

	public void setEditionNumberOfPages(int editionNumberOfPages) {
		this.editionNumberOfPages = editionNumberOfPages;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public int getCurrentLocationID() {
		return currentLocationID;
	}

	public void setCurrentLocationID(int currentLocationID) {
		this.currentLocationID = currentLocationID;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
}
