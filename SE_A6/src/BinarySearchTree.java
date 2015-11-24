
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
	
	public V search(K key) {
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
		return null;	//key not found in tree, return null
	}
	
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
	
	public void delete (K key) {
		delete(root, key);
	}
	
	private void delete (BinaryNode rootNode, K key) {
		BinaryNode x = rootNode;
		BinaryNode parent = null;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) {
				parent = x;
				x = x.leftChild;
			}
			else if (cmp > 0) {
				parent = x;
				x = x.rightChild;
			}
			else {
				BinaryNode append;
				if (x.rightChild == null || x.leftChild == null){
					if (x.leftChild == null)
						append = x.rightChild;
					else
						append = x.leftChild;
					if (parent.leftChild.equals(x))
						parent.leftChild = append;
					else
						parent.rightChild = append;
				}
				else {
					append = treeMinimum(x.rightChild);
					parent.value = append.value;
					delete(x.rightChild, append.key);
				}
			}
		}
	}
	
	private BinaryNode treeMinimum(BinaryNode x){
		while (x.leftChild != null)
			x = x.leftChild;
		return x;
	}
}

