package knjiznica.model;

public class User {
	
	private int ID;

	private String firstName;
	
	private String middleName;
	
	private String lastName;

	private String country;
	
	private int postalCode;
	
	private String street;
	
	private String houseNumber;
	
	private String phoneNumber;
	
	private String email;
	
	private String city;
	
	private int addressID;
	
	public User(int ID, String firstName, String middleName, String lastName, String country, int postalCode, String street, String houseNumber, String phoneNumber, String email, String city, Integer addressID) {
		this.ID = ID;
		this.firstName = firstName;
		this.middleName = middleName; 
		this.lastName = lastName;
		this.country = country;
		this.postalCode = postalCode;
		this.street = street;
		this.houseNumber = houseNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.city = city;
		this.addressID = addressID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
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
	
}
