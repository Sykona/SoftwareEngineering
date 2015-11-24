
public class Test {
	public static void main (String[] args){
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>();
		
		bst.insert(new Integer(5), "Olli ist die Root");
		bst.insert(new Integer(3), "Sebi das linke Kind von olli");
		bst.insert(new Integer(1), "linke kind von sebi ist zigarette");
		bst.insert(new Integer(4), "rechte kind von sebi ist das Bier");
		bst.insert(new Integer(7), "rechtes kind von olli ist danny");
		bst.insert(new Integer(6), "linkes kind von danny ist ganja");
		bst.insert(new Integer(8), "rechtes kind von danny ist vodka");
		bst.delete(new Integer(7));
		System.out.println("fertig");
		
	}
}
