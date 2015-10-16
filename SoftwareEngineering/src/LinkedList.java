
public class LinkedList<T> {
	
	private LinkedListElement head;
	
	private class LinkedListElement{
		private LinkedListElement successor;
		private T item;
		
		private LinkedListElement(T item){
			this.item = item;
		}
	}

	public void add(T item){
		LinkedListElement newElement = new LinkedListElement(item);
		newElement.successor = head;
		head = newElement;
	}
	
	public T get(){
		if(head == null){
			throw new RuntimeException("stack empty");
		}
		LinkedListElement getElement = head;
		head = head.successor;
		return getElement.item;
	}
	
}
