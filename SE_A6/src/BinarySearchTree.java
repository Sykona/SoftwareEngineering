/**
 * This class represents a StandardBinarySearchTree with the Methods search, insert, delete
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 *
 * @param <V> the generic type of the values of the tree
 * @param <K> the generic type of the keys of the tree
 * 				which must implement the Comparable interface in order to
 * 				have compareTo implemented
 */
public class BinarySearchTree <V, K extends Comparable<K>> {
	
	private BinaryNode root;
	
	private class BinaryNode{
		private BinaryNode leftChild, rightChild;
		private K key;
		private V value;
		
		public BinaryNode(K key, V value){
			this.key = key;
			this.value = value;
		}
	}
	
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Searches for the node with the given key in the BinarySearchTree
	 * @param key the key of the node we are searching
	 * @return the value of the key, if its found
 	 * @throws KeyNotFoundException if the key is not found in the tree
	 */
	public V search(K key) throws KeyNotFoundException{
		BinaryNode x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.leftChild;
			else if (cmp > 0)
				x = x.rightChild;
			else
				return x.value;
		}
		throw new KeyNotFoundException("Node with key " + key + " not found in the Tree");
	}
	
	/**
	 * Inserts the given key,value pair into the BinarySearchTree
	 * @param key the key of the pair we want to insert
	 * @param value the value of the pair we want to insert
	 */
	public void insert (K key, V value) {
		BinaryNode x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) {
				if (x.leftChild == null) {
					x.leftChild = new BinaryNode(key, value);
					return;
				}
				else
					x = x.leftChild;
			}
			else if (cmp > 0) {
				if (x.rightChild == null) {
					x.rightChild = new BinaryNode(key, value);
					return;
				}
				else
					x = x.rightChild;
			}
		}
		root = new BinaryNode(key, value);	//if root is null, set new root
	}
	
	/**
	 * Deletes the node with the given key from the BinarySearchTree
	 * @param key the key of the node we want to delete
	 */
	public void delete (K key) {
		BinaryNode parent = root;
		BinaryNode x = root;
		boolean isLeftChild = false;
		while (key.compareTo(x.key) != 0) {
			parent = x;
		    int cmp = key.compareTo(x.key);
		    if (cmp < 0) {
		    	isLeftChild = true;
		    	x = x.leftChild;
		    }
		    else {
		    	isLeftChild = false;
		    	x = x.rightChild;
		    }
		    if (x == null)
		    	return;
		}
		// now we have found the node we want to delete
		// Case 1: node has no children
		if (x.leftChild == null && x.rightChild == null) {
			if (x == root)
				root = null;
			if (isLeftChild == true) 
				parent.leftChild = null;
			else
				parent.rightChild = null;
		}
		//Case 2: node has only one child
		else if (x.rightChild == null) {
			if (x == root)
				root = x.leftChild;
			else if (isLeftChild) 
				parent.leftChild = x.leftChild;
			else
				parent.rightChild = x.leftChild;
		}
		else if (x.leftChild == null) {
			if (x == root)
				root = x.rightChild;
			else if (isLeftChild) 
				parent.leftChild = x.rightChild;
			else
				parent.rightChild = x.rightChild;
		}
		//Case 3: node has two childs
		else if (x.leftChild != null && x.rightChild != null) {
			//get successor
			BinaryNode successor = getSuccessor(x);
			if (x == root)
				root = successor;
			else if (isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			successor.leftChild = x.leftChild;
		}
	}
	
	private BinaryNode getSuccessor (BinaryNode rootNode) {
		BinaryNode successor = null;
		BinaryNode sucessorParent = null;
		BinaryNode x = rootNode.rightChild;
		while (x != null) {
			sucessorParent = successor;
			successor = x;
			x = x.leftChild;
		}
		if (successor != rootNode.rightChild) {
			sucessorParent.leftChild = successor.rightChild;
			successor.rightChild = rootNode.rightChild;
		}
		return successor;
	}
}

