package tree;

public class Utils {

/**
 * Print the node info, such as parent and right and left sons 
 * @param node node of tree
 */
	private static void printNode(TreeNode node) {
			
		if(node != null) {
			
			TreeNode left=node.getLeft();
			TreeNode right=node.getRight();
			TreeNode parent=node.getParent();
			if((parent != null) && (left != null || right != null) )
				System.out.println("[Nodo] : "+node.getKey());
			else if(parent == null) 
				System.out.println("[Radice] : "+node.getKey());
			else if (left == null && right == null ) 
				System.out.println("[Foglia] : "+node.getKey());

			if (parent != null) {
				System.out.println("\t[Padre] \t: "+parent.getKey());
			}else 
				System.out.println("\t[Padre] \t: null");
			if (left != null)
				System.out.println("\t[Sinistra] \t: "+left.getKey());
			else 
				System.out.println("\t[Sinistra] \t: No Figlio");
			if (right != null)
				System.out.println("\t[Destra] \t: "+right.getKey());
			else 
				System.out.println("\t[Destra] \t: No Figlio");
		}
	}
	
/**
 * Print all node in tree
 * @param root root of tree
 */
	public static void printTree(TreeNode root) {
			if (root != null) {
				printNode(root);
				printTree(root.getLeft());
				printTree(root.getRight());
			}
	}
	
	public static void inorder_TreeWalk(TreeNode root) {
		if (root != null) {
			printTree(root.getLeft());
			printNode(root);
			printTree(root.getRight());
		}
	}
}
