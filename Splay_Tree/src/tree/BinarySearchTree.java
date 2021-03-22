package tree;

/**
 * Albero binario di ricerca base
 * @author jiin995
 *
 */
public class BinarySearchTree {
	
	protected TreeNode root;
	
	public BinarySearchTree (TreeNode r) {
			this.root=r;
	}
		
	public BinarySearchTree () {
		this.root=null;
	}
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	/**
	 * Inserisce un nuovo nodo all'interno dell'albero
	 * @param z
	 */
	public void insert(TreeNode z) {
		TreeNode y =null;
		TreeNode x=this.root;
		while (x != null ) {
			y = x;
			if (z.getKey()<x.getKey())
				x=x.getLeft();
			else
				x=x.getRight();
		}
		z.setParent(y);
		if (y==null) 
			this.root=z;
		else {
			if (z.getKey()<y.getKey())
				y.setLeft(z);
			else
				y.setRight(z);
		}
	}
	
	public TreeNode treeMinimum(TreeNode x) {
		if(x!=null) {
			while(x.getLeft() != null) {
				x=x.getLeft();
			}
		}
		return x;
	}
	
	public TreeNode treeMaximum(TreeNode x) {
		if(x!=null) {
			while(x.getRight() != null) {
				x=x.getRight();
			}
		}
		return x;
	}
	
	
	public TreeNode treeSuccessor(TreeNode x) {
		TreeNode y=null;
		if(x!=null) {
			if(x.getRight()!=null)
				return treeMinimum(x.getRight());
	
			y=x.getParent();
			
			while ((y != null) && (x==y.getRight())) {
				x=y;
				y=y.getParent();
			}
		}
		return y;
	}
	
	public TreeNode delete(TreeNode z) {
		TreeNode y=null;
		TreeNode x=null;
		if ((z.getLeft()==null) || (z.getRight()==null))
			y=z;
		else
			y=treeSuccessor(z);
			System.out.println("Successore di "+z.getKey()+" e' "+y.getKey());
		
		if (y.getLeft()!=null)
			x=y.getLeft();
		else
			x=y.getRight();
		
		if (x!= null)
			x.setParent(y.getParent());
		
		if (y.getParent()==null) 
				this.root=x;
		else if(y.getParent().getLeft()==y)
				y.getParent().setLeft(x);
			else
				y.getParent().setRight(x);
		
		if(z != y) {
			z.setKey(y.getKey());
		}
		
		return y;
	}
	
	public TreeNode search(TreeNode x,int k) {
		TreeNode y=x;
		while ((y!=null) && (y.getKey()!=k)) {
			if (k < y.getKey()) 
				y=y.getLeft();
			else
				y=y.getRight();
		}
		
		return y;
	}
	/**
	 * Operazione di rotazione a sinistra del nodo x
	 * @param x
	 */
	public void leftRotate(TreeNode x) {
		TreeNode y=x.getRight();
		
		if(y != null) {
		//il figlio di sinistra di y diventa il figlio di destra di x
			x.setRight(y.getLeft());
			if( y.getLeft() != null ) {
					y.getLeft().setParent(x);
			}	
		
			// il padre di x diventa il padre di y
			y.setParent(x.getParent());
			if ( x.getParent() == null ) {
				this.root=y;
			}else if (x == x.getParent().getLeft()) {
				x.getParent().setLeft(y);
			}else {
				x.getParent().setRight(y);
			}
			
			//x diventa il figlio di sinistra di y
			y.setLeft(x);
			x.setParent(y);
		}
	}
	
	/**
	 * Rotazione a destra del nodo x
	 * @param x
	 */
	public void rightRotate(TreeNode x) {
		TreeNode y=x.getLeft();
		
		if(y!= null) {
		//il figlio di destra di y diventa il figlio di sinistra di x
			x.setLeft(y.getRight());
			if(y.getRight()!=null) {
				y.getRight().setParent(x);
			}
			//il padre di x diventa il padre di y
			y.setParent(x.getParent());
		
	
			if(x.getParent()==null) {
				this.root=y;
			}else if (x == x.getParent().getLeft()) {
				x.getParent().setLeft(y);
			}else {
				x.getParent().setRight(y);
			}
		
			//x diventa il figlio di destra di y 
			y.setRight(x);
			x.setParent(y);
		}
	}
}
