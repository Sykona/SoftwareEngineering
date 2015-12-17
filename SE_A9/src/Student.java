/**
 * The Class Student.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public class Student {


	private final int matNr;
	private String name;
	private String surname;
	
	/**
	 * Instantiates a new student.
	 *
	 * @param matNr the matriculation number
	 * @param name the name
	 * @param surname the surname
	 */
	public Student(int matNr, String name, String surname) {
		this.matNr = matNr;
		this.surname = surname;
		this.name = name;
	}

	/**
	 * Gets the matriculation number.
	 *
	 * @return the matriculation number
	 */
	public int getMatNr() {
		return matNr;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name + " " + surname + ", " + matNr;
	}
	
	/**
	 * Equals.
	 *
	 * @param student the student
	 * @return true, if same matriculation number
	 */
	public boolean equals(Student student) {
		return matNr == student.getMatNr();
	}
}
