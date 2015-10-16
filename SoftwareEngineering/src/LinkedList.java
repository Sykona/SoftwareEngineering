/**
 * Basic and short implementation of a linked
 * list, using a private class for those elements.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 * 
 * @param <T> generic type argument T
 */
public class LinkedList<T> {
	
	/**
	 * Private class implementation for the elements 
	 * of the linked list.
	 */
	private class LinkedListElement{
		private LinkedListElement successor;
		private T item;
		
		private LinkedListElement(T item){
			this.item = item;
		}
	}
	
	private LinkedListElement head;
	
	/**
	 * Basic operation for adding items to the list
	 * @param item the item to add
	 */
	public void add(T item){
		LinkedListElement newElement = new LinkedListElement(item);
		newElement.successor = head;
		head = newElement;
	}
	
	/**
	 * Basic operation for returning the first item of the list
	 * @return	the first item of the list
	 */
	public T get(){
		if(head == null){
			throw new RuntimeException("stack empty");
		}
		LinkedListElement getElement = head;
		head = head.successor;
		return getElement.item;
	}
	
}
