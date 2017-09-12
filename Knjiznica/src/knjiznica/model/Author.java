package knjiznica.model;

public class Author {
	private int ID;
	private String firstName;
	private String middleName;
	private String lastName;
	private boolean isAlive;
	private String yearOfBirth;
	private String yearOfDeath;
	
	public Author(int ID, String firstName, String middleName, String lastName, boolean isAlive, String yearOfBirth, String yearOfDeath) {
		this.ID = ID;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.isAlive = isAlive;
		this.yearOfBirth = yearOfBirth;
		this.yearOfDeath = yearOfDeath;
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

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getYearOfDeath() {
		return yearOfDeath;
	}

	public void setYearOfDeath(String yearOfDeath) {
		this.yearOfDeath = yearOfDeath;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
