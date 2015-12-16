import java.util.ArrayList;
import java.util.TreeMap;

public class StudentManager {
	
	public enum Event {
		ADD, RENAME, REMOVE;
	}

	private ArrayList<Observer> observers;
	private TreeMap<Integer,Student> students;
	
	public StudentManager() {
		observers = new ArrayList<Observer>();
		students = new TreeMap<Integer,Student>();
	}
	
	public void addObserver(Observer obs) {
		observers.add(obs);
	}
	
	public void removeObserver(Observer obs) {
		observers.remove(obs);
	}
	
	public void notifyObservers(Student s, Event e) {	
		for(Observer obs : observers)
			obs.update(s, e);
	}
	
	public void addStudent(Student s) {
		
		// if a student with an already existing matNr is added,
		// replace old with new in case we don't have map structure
		if (students.get(s.getMatNr()) != null)
			notifyObservers(students.get(s.getMatNr()), Event.REMOVE);
			
		students.put(s.getMatNr(), s);
		notifyObservers(s, Event.ADD);
	}
	
	public void renameStudent(Student s, String name, String surname) {
		students.get(s.getMatNr()).setName(name);
		students.get(s.getMatNr()).setSurname(surname);
		notifyObservers(s, Event.RENAME);
	}
	
	public void removeStudent(Student s) {
		students.remove(s.getMatNr());
		notifyObservers(s, Event.REMOVE);
	}
}
