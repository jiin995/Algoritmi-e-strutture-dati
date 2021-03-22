package tree;

public class TreeNode {
	
	private TreeNode parent;
	private TreeNode right;
	private TreeNode left;
	private int key;
	
	public TreeNode(TreeNode parent, TreeNode right, TreeNode left, int data) {
		super();
		this.parent = parent;
		this.right = right;
		this.left = left;
		this.key = data;
	}
	
	public int getKey() {
		return key;
	}
	public void setKey(int data) {
		this.key = data;
	}
	public TreeNode getParent() {
		return parent;
	}
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
}
