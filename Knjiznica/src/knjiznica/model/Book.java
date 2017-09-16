package knjiznica.model;

import java.sql.Date;
import java.util.ArrayList;

public class Book {
	
	private int ID;
	private String ISBN;
	private String title;
	private String summary;
	private int editionID;
	private int editionNumber;
	private String editionYear;
	private int editionNumberOfPages;
	private Object currentLocation;
	private Object owner;
	private boolean available;
	private Date returnDate;
	private String information;
	private ArrayList<Author> authors;
	private ArrayList<Publisher> publishers;
	private ArrayList<Language> languages;
	private ArrayList<Genre> genres;
	private String currentLocationName;
	private String ownerName;
	private String authorsName;
	private String publishersName;
	private String languagesName;
	private String genresName;
	
	public Book(int ID, String ISBN, String title, String summary, int editionID, 
			int editionNumber, String editionYear, int editionNumberOfPages, 
			Object currentLocation, Object owner, boolean available, Date returnDate, 
			String information, ArrayList<Author> authors, ArrayList<Publisher> publishers, 
			ArrayList<Language> languages, ArrayList<Genre> genres, String currentLocationName, String ownerName, String authorsName, String publishersName, String languagesName, String genresName) {
		this.ID = ID;
		this.ISBN = ISBN;
		this.title = title;
		this.summary = summary;
		this.editionID = editionID;
		this.editionNumber = editionNumber;
		this.editionYear = editionYear;
		this.editionNumberOfPages = editionNumberOfPages;
		this.currentLocation = currentLocation;
		this.owner = owner;
		this.available = available;
		this.returnDate = returnDate;
		this.information = information;
		this.authors = authors;
		this.publishers = publishers;
		this.languages = languages;
		this.genres = genres;
		this.currentLocationName = currentLocationName;
		this.ownerName = ownerName;
		this.authorsName = authorsName;
		this.publishersName = publishersName;
		this.languagesName = languagesName;
		this.genresName = genresName;
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

	public Object getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Object currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Object getOwner() {
		return owner;
	}

	public void setOwner(Object owner) {
		this.owner = owner;
	}

	public ArrayList<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}

	public ArrayList<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(ArrayList<Publisher> publishers) {
		this.publishers = publishers;
	}

	public ArrayList<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(ArrayList<Language> languages) {
		this.languages = languages;
	}

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
	}

	public String getCurrentLocationName() {
		return currentLocationName;
	}

	public void setCurrentLocationName(String currentLocationName) {
		this.currentLocationName = currentLocationName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getAuthorsName() {
		return authorsName;
	}

	public void setAuthorsName(String authorsName) {
		this.authorsName = authorsName;
	}

	public String getPublishersName() {
		return publishersName;
	}

	public void setPublishersName(String publishersName) {
		this.publishersName = publishersName;
	}

	public String getLanguagesName() {
		return languagesName;
	}

	public void setLanguagesName(String languagesName) {
		this.languagesName = languagesName;
	}

	public String getGenresName() {
		return genresName;
	}

	public void setGenresName(String genresName) {
		this.genresName = genresName;
	}
	
}
