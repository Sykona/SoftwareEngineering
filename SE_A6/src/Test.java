
public class Test {
	public static void main (String[] args){
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>();
		
		bst.insert(new Integer(5), "");
		bst.insert(new Integer(3), "");
		bst.insert(new Integer(1), "");
		bst.insert(new Integer(4), "");
		bst.insert(new Integer(7), "");
		bst.insert(new Integer(6), "");
		bst.insert(new Integer(8), "");
		bst.delete(new Integer(5));
		
	}
}
