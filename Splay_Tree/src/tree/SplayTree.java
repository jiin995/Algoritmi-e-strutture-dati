package tree;

/**
 * Un albero binario di ricerca soddisfa la seguente proprietà.
 * Sia x un nodo dell'albero. Se y è nel sottoalbero di sinistra di x, 
 * allora key[y]<=key[x]. Se y è nel sottoalbero di destra di x, allora key[y]>=key[x].
 * Gli alberi auto-aggiustanti (splay trees) sono alberi binari di ricerca che non impongono ulteriori vincoli sulla disposizione degli elementi
 * @author jiin995
 *
 */
public class SplayTree extends BinarySearchTree {
	
	public SplayTree() {
		super();
	};
	
	public SplayTree(TreeNode r) {
		super(r);
	}
	
	public void splay(TreeNode x) {
		//TreeNode grandParent = (parent != null) ? parent.getParent() : null;
		// se x è la radice l'operazione di Splay non ha senso
		TreeNode parent=x.getParent();

		while( parent != null) {

			TreeNode grandpa=parent.getParent();
			// x è figlio della radice, allora per rispettare la proprietà degli alberi binari di ricerca
			// bisogna effettuare una rotazione a sinistra sulla radice
			if(grandpa== null ) {
				if ( parent.getRight()==x)
					this.leftRotate(x.getParent());
				else
					this.rightRotate(x.getParent());
			}else {
				if((grandpa.getLeft()==parent)&&(parent.getLeft()==x)) {
					//System.out.println("Entrambi figli di sinistra");
					this.rightRotate(grandpa);
					this.rightRotate(parent);
				}else if((grandpa.getRight()==parent)&&(parent.getRight()==x)) {
					//System.out.println("Entrambi figli di destra");
					this.leftRotate(grandpa);
					this.leftRotate(parent);
				}else if ((grandpa.getRight()==parent)&&(parent.getLeft()==x)) {
					//System.out.println("leftRotate su "+grandpa.getKey()+"RightRotate su "+parent.getKey());
					//System.out.println("Splay: padre figlio di destra e nuovo nodo figlio di sinistra");
					this.rightRotate(parent);
					//System.out.println("RightRotate su "+parent.getKey());
					//Utils.printTree(root);
					this.leftRotate(grandpa);
					//System.out.println("leftRotate su "+grandpa.getKey());
					//Utils.printTree(root);
				}else if ((grandpa.getLeft()==parent)&&(parent.getRight()==x)) {
					//System.out.println("leftRotate su "+parent.getKey()+"RightRotate su "+grandpa.getKey());
					//System.out.println("Splay padre figlio di sinistra e nuovo nodo figlio di destra");
					this.leftRotate(parent);
					//System.out.println("leftRotate su "+parent.getKey());
					//Utils.printTree(root);
					this.rightRotate(grandpa);
					//System.out.println("RightRotate su "+grandpa.getKey());
					//Utils.printTree(root);
				}
			}
			parent=x.getParent();
		}	
	}
	
	
	public void insert (TreeNode z) {
		super.insert(z);
		splay(z);
	}
	
	//delete del libro
	public TreeNode deleteOld(TreeNode z) {
		TreeNode y=super.delete(z);
		if(z.getParent()!=null) 
			//System.out.println("Eseguo la splay sul padre di y"+y.getKey());
			splay(z.getParent());
		return y;
	}
	
	//https://www.geeksforgeeks.org/splay-tree-set-3-delete/
	//Con la vecchia implementazione si applicava 2 volte la splay
	//1 volta per cercare e una volta per eseguire la delete
	//Ritorna la nuova radice!
	//Alle 02:39 ho trovato l'arcano
	public TreeNode delete(TreeNode z) {

		if ((root != null ) && (z != null )) {
			TreeNode y=z;
			TreeNode leftTree=z.getLeft();
			TreeNode rightTree=z.getRight();
		
			if(leftTree != null) {
				//System.out.println("Eseguo la splay sul padre di y"+y.getKey());
				y=treeMaximum(leftTree);
				//System.out.println("Eseguo la splay su : " +y.getKey());
				splay(y);
				//System.out.println("Nuova Radice: " +root.getKey());
				if(root != y) {
					System.out.println("qualcosa non va");
				}
				if(rightTree!=null) {
					root.setRight(rightTree);
					rightTree.setParent(root);
				}
				return root;
			}else 
				root=rightTree;
				rightTree.setParent(null);
				return rightTree;
		}else 
			return null;
	}
	
	public TreeNode search(int k) {
		TreeNode x=this.root;
		TreeNode y=null;
		
		while((x != null) && (x.getKey()!=k)) {
			y=x;
			if (k < x.getKey())
				x=x.getLeft();
			else
				x=x.getRight();
		}
		
		if(x != null)
			splay(x);
		else if (this.root != null)
			splay(y);
	
		return x;
	}
	
}
	