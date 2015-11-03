package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.ObjectOutputStream;

public class ArrayListDummy extends AbstractList implements List, RandomAccess, Cloneable, Serializable {

	private static final long serialVersionUID = 0L;
	private static final int DEFAULT_CAPACITY = 0;
	private static final Object[] EMPTY_ELEMENTDATA = null;
	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = null;
	transient Object[] elementData = null;
	private int size = 0;
	private static final int MAX_ARRAY_SIZE = 0;


	public ArrayListDummy(Collection arg0) {
		System.out.println("constructor");
}

	public ArrayListDummy() {
		System.out.println("constructor");
}

	public ArrayListDummy(int arg0) {
		System.out.println("constructor");
}

	public boolean add(Object arg0) {
		System.out.println("add");
		return false;
	}

	public void add(int arg0, Object arg1) {
		System.out.println("add");
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

	public boolean isEmpty() {
		System.out.println("isEmpty");
		return false;
	}

	public int lastIndexOf(Object arg0) {
		System.out.println("lastIndexOf");
		return 0;
	}

	public boolean contains(Object arg0) {
		System.out.println("contains");
		return false;
	}

	public void replaceAll(UnaryOperator arg0) {
		System.out.println("replaceAll");
	}

	public int size() {
		System.out.println("size");
		return 0;
	}

	public List subList(int arg0, int arg1) {
		System.out.println("subList");
		return null;
	}

	public Object[] toArray(Object[] arg0) {
		System.out.println("toArray");
		return null;
	}

	public Object[] toArray() {
		System.out.println("toArray");
		return null;
	}

	public Iterator iterator() {
		System.out.println("iterator");
		return null;
	}

	public Spliterator spliterator() {
		System.out.println("spliterator");
		return null;
	}

	public boolean addAll(int arg0, Collection arg1) {
		System.out.println("addAll");
		return false;
	}

	public boolean addAll(Collection arg0) {
		System.out.println("addAll");
		return false;
	}

	static int access$100(ArrayList arg0) {
		System.out.println("access$100");
		return 0;
	}

	private void readObject(ObjectInputStream arg0) throws IOException, ClassNotFoundException {
		System.out.println("readObject");
	}

	private void writeObject(ObjectOutputStream arg0) throws IOException {
		System.out.println("writeObject");
	}

	public void forEach(Consumer arg0) {
		System.out.println("forEach");
	}

	public Object set(int arg0, Object arg1) {
		System.out.println("set");
		return null;
	}

	public void ensureCapacity(int arg0) {
		System.out.println("ensureCapacity");
	}

	public void trimToSize() {
		System.out.println("trimToSize");
	}

	private void ensureCapacityInternal(int arg0) {
		System.out.println("ensureCapacityInternal");
	}

	Object elementData(int arg0) {
		System.out.println("elementData");
		return null;
	}

	private void grow(int arg0) {
		System.out.println("grow");
	}

	private static int hugeCapacity(int arg0) {
		System.out.println("hugeCapacity");
		return 0;
	}

	public boolean removeAll(Collection arg0) {
		System.out.println("removeAll");
		return false;
	}

	public boolean retainAll(Collection arg0) {
		System.out.println("retainAll");
		return false;
	}

	protected void removeRange(int arg0, int arg1) {
		System.out.println("removeRange");
	}

	public ListIterator listIterator() {
		System.out.println("listIterator");
		return null;
	}

	public ListIterator listIterator(int arg0) {
		System.out.println("listIterator");
		return null;
	}

	public boolean removeIf(Predicate arg0) {
		System.out.println("removeIf");
		return false;
	}

	public void sort(Comparator arg0) {
		System.out.println("sort");
	}

	private void rangeCheckForAdd(int arg0) {
		System.out.println("rangeCheckForAdd");
	}

	private String outOfBoundsMsg(int arg0) {
		System.out.println("outOfBoundsMsg");
		return null;
	}

	private void ensureExplicitCapacity(int arg0) {
		System.out.println("ensureExplicitCapacity");
	}

	private void fastRemove(int arg0) {
		System.out.println("fastRemove");
	}

	private void rangeCheck(int arg0) {
		System.out.println("rangeCheck");
	}

	private boolean batchRemove(Collection arg0, boolean arg1) {
		System.out.println("batchRemove");
		return false;
	}

	static void subListRangeCheck(int arg0, int arg1, int arg2) {
		System.out.println("subListRangeCheck");
	}

}