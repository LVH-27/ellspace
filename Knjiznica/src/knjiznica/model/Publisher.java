package knjiznica.model;

public class Publisher {
	private int ID;
	private String name;
	private String country;
	private String postalCode;
	private String street;
	private String houseNumber;
	private String cityName;
	private int addressID;
	
	public Publisher(int ID, String name, String country, String postalCode, String street, String houseNumber, String cityName, int addressID) {
		this.ID = ID;
		this.name = name;
		this.country = country;
		this.postalCode = postalCode;
		this.street = street;
		this.houseNumber = houseNumber;
		this.cityName = cityName;
		this.addressID = addressID;
		
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	
}
