/**
 * The Interface Observer. 
 * All Observers should implement this Interface, so they can be notified on changes.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public interface Observer {

	/**
	 * Notifies the Observer about changes, so it can update it's view.
	 *
	 * @param s the student
	 * @param e the event
	 */
	public void update(Student s, StudentManager.Event e);
}
