package knjiznica.model;

public class Genre {
	
	private int ID;
	private String name;
	private String nameHr;
	private String nameDe;
	
	public Genre(int ID, String name, String nameHr, String nameDe) {
		this.ID = ID;
		this.name = name;
		this.nameHr = nameHr;
		this.nameDe = nameDe;
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

	public String getNameHr() {
		return nameHr;
	}

	public void setNameHr(String nameHr) {
		this.nameHr = nameHr;
	}

	public String getNameDe() {
		return nameDe;
	}

	public void setNameDe(String nameDe) {
		this.nameDe = nameDe;
	}
	
}
