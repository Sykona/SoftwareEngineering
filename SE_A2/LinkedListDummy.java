package java.util;

import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LinkedListDummy extends AbstractSequentialList implements List, Deque, Cloneable, Serializable {

	transient int size = 0;
	transient Node first = null;
	transient Node last = null;
	private static final long serialVersionUID = 0L;


	public LinkedListDummy() {
		System.out.println("constructor");
}

	public LinkedListDummy(Collection arg0) {
		System.out.println("constructor");
}

	private void linkFirst(Object arg0) {
		System.out.println("linkFirst");
	}

	void linkLast(Object arg0) {
		System.out.println("linkLast");
	}

	void linkBefore(Object arg0, Node arg1) {
		System.out.println("linkBefore");
	}

	private Object unlinkFirst(Node arg0) {
		System.out.println("unlinkFirst");
		return null;
	}

	private Object unlinkLast(Node arg0) {
		System.out.println("unlinkLast");
		return null;
	}

	Object unlink(Node arg0) {
		System.out.println("unlink");
		return null;
	}

	private boolean isElementIndex(int arg0) {
		System.out.println("isElementIndex");
		return false;
	}

	private boolean isPositionIndex(int arg0) {
		System.out.println("isPositionIndex");
		return false;
	}

	private void checkElementIndex(int arg0) {
		System.out.println("checkElementIndex");
	}

	private void checkPositionIndex(int arg0) {
		System.out.println("checkPositionIndex");
	}

	Node node(int arg0) {
		System.out.println("node");
		return null;
	}

	private LinkedList superClone() {
		System.out.println("superClone");
		return null;
	}

	public void addFirst(Object arg0) {
		System.out.println("addFirst");
	}

	public void addLast(Object arg0) {
		System.out.println("addLast");
	}

	public boolean offerFirst(Object arg0) {
		System.out.println("offerFirst");
		return false;
	}

	public boolean offerLast(Object arg0) {
		System.out.println("offerLast");
		return false;
	}

	public Object removeFirst() {
		System.out.println("removeFirst");
		return null;
	}

	public Object removeLast() {
		System.out.println("removeLast");
		return null;
	}

	public Object pollFirst() {
		System.out.println("pollFirst");
		return null;
	}

	public Object pollLast() {
		System.out.println("pollLast");
		return null;
	}

	public Object getLast() {
		System.out.println("getLast");
		return null;
	}

	public Object peekFirst() {
		System.out.println("peekFirst");
		return null;
	}

	public Object peekLast() {
		System.out.println("peekLast");
		return null;
	}

	public boolean removeFirstOccurrence(Object arg0) {
		System.out.println("removeFirstOccurrence");
		return false;
	}

	public boolean removeLastOccurrence(Object arg0) {
		System.out.println("removeLastOccurrence");
		return false;
	}

	public boolean offer(Object arg0) {
		System.out.println("offer");
		return false;
	}

	public Object element() {
		System.out.println("element");
		return null;
	}

	public Iterator descendingIterator() {
		System.out.println("descendingIterator");
		return null;
	}

	public void add(int arg0, Object arg1) {
		System.out.println("add");
	}

	public boolean add(Object arg0) {
		System.out.println("add");
		return false;
	}

	public Object remove() {
		System.out.println("remove");
		return null;
	}

	public boolean remove(Object arg0) {
		System.out.println("remove");
		return false;
	}

	public Object remove(int arg0) {
		System.out.println("remove");
		return null;
	}

	public Object get(int arg0) {
		System.out.println("get");
		return null;
	}

	public Object clone() {
		System.out.println("clone");
		return null;
	}

	public int indexOf(Object arg0) {
		System.out.println("indexOf");
		return 0;
	}

	public void clear() {
		System.out.println("clear");
	}

	public int lastIndexOf(Object arg0) {
		System.out.println("lastIndexOf");
		return 0;
	}

	public boolean contains(Object arg0) {
		System.out.println("contains");
		return false;
	}

	public int size() {
		System.out.println("size");
		return 0;
	}

	public Object[] toArray() {
		System.out.println("toArray");
		return null;
	}

	public Object[] toArray(Object[] arg0) {
		System.out.println("toArray");
		return null;
	}

	public Spliterator spliterator() {
		System.out.println("spliterator");
		return null;
	}

	public boolean addAll(Collection arg0) {
		System.out.println("addAll");
		return false;
	}

	public boolean addAll(int arg0, Collection arg1) {
		System.out.println("addAll");
		return false;
	}

	public Object getFirst() {
		System.out.println("getFirst");
		return null;
	}

	public void push(Object arg0) {
		System.out.println("push");
	}

	public Object pop() {
		System.out.println("pop");
		return null;
	}

	private void readObject(ObjectInputStream arg0) throws IOException, ClassNotFoundException {
		System.out.println("readObject");
	}

	private void writeObject(ObjectOutputStream arg0) throws IOException {
		System.out.println("writeObject");
	}

	public Object poll() {
		System.out.println("poll");
		return null;
	}

	public Object set(int arg0, Object arg1) {
		System.out.println("set");
		return null;
	}

	public Object peek() {
		System.out.println("peek");
		return null;
	}

	public ListIterator listIterator(int arg0) {
		System.out.println("listIterator");
		return null;
	}

	private String outOfBoundsMsg(int arg0) {
		System.out.println("outOfBoundsMsg");
		return null;
	}

}