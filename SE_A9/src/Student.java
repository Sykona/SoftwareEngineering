public class Student {

	private final int matNr;
	private String name;
	private String surname;
	
	public Student(int matNr, String name, String surname) {
		this.matNr = matNr;
		this.surname = surname;
		this.name = name;
	}

	public int getMatNr() {
		return matNr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String toString() {
		return name + " " + surname + ", " + matNr;
	}
	
	public boolean equals(Student student) {
		return matNr == student.getMatNr();
	}
}
