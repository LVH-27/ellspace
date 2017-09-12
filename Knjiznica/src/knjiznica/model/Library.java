package knjiznica.model;

public class Library {
	
	private int ID;
	private String firstName;
	private String country;
	private String postalCode;
	private String street;
	private String houseNumber;
	private String phoneNumber;
	private String email;
	private String information;
	private String city;
	private String opens;
	private String closes;
	private int addressID;
	
	public Library(int ID, String firstName, String country, String postalCode, String street, String houseNumber, String phoneNumber, String email, String information, String city, Integer addressID) {
		this.ID = ID;
		this.firstName = firstName;
		this.country = country;
		this.postalCode = postalCode;
		this.street = street;
		this.houseNumber = houseNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.city = city;
		this.addressID = addressID;
		this.information = information;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getOpens() {
		return opens;
	}

	public void setOpens(String opens) {
		this.opens = opens;
	}

	public String getCloses() {
		return closes;
	}

	public void setCloses(String closes) {
		this.closes = closes;
	}	
	
}
