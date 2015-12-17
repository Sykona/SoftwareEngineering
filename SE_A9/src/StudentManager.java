import java.util.ArrayList;
import java.util.TreeMap;

/**
 * The Class StudentManager.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public class StudentManager {
	
	/**
	 * The Enum Event.
	 */
	public enum Event {
		ADD,  RENAME,  REMOVE;
	}


	private ArrayList<Observer> observers;
	private TreeMap<Integer,Student> students;
	
	/**
	 * Instantiates a new student manager.
	 * Students stored in a Treemap, since we don't want duplicates and we can sort by matriculation number
	 */
	public StudentManager() {
		observers = new ArrayList<Observer>();
		students = new TreeMap<Integer,Student>();
	}
	
	/**
	 * Adds an observer.
	 *
	 * @param obs the observer
	 */
	public void addObserver(Observer obs) {
		observers.add(obs);
	}
	
	/**
	 * Removes an observer.
	 *
	 * @param obs the observer
	 */
	public void removeObserver(Observer obs) {
		observers.remove(obs);
	}
	
	/**
	 * Notifies all observers.
	 *
	 * @param s the student
	 * @param e the event
	 */
	public void notifyObservers(Student s, Event e) {	
		for(Observer obs : observers)
			obs.update(s, e);
	}
	
	/**
	 * Adds a student.
	 *
	 * @param s the student
	 */
	public void addStudent(Student s) {
		
		// if a student with an already existing matNr is added,
		// replace old with new in case we don't have map structure in our observer-views
		if (students.get(s.getMatNr()) != null)
			notifyObservers(students.get(s.getMatNr()), Event.REMOVE);
			
		students.put(s.getMatNr(), s);
		notifyObservers(s, Event.ADD);
	}
	
	/**
	 * Rename student.
	 *
	 * @param s the student
	 * @param name the name
	 * @param surname the surname
	 */
	public void renameStudent(Student s, String name, String surname) {
		students.get(s.getMatNr()).setName(name);
		students.get(s.getMatNr()).setSurname(surname);
		notifyObservers(s, Event.RENAME);
	}
	
	/**
	 * Removes a student.
	 *
	 * @param s the student
	 */
	public void removeStudent(Student s) {
		students.remove(s.getMatNr());
		notifyObservers(s, Event.REMOVE);
	}
}
