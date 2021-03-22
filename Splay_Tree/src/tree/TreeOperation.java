package tree;

public class TreeOperation {
	
	/**
	 *  operation
	 *  0 insert
	 *  1 delete
	 *  2 search
	 */
	public static final int INSERT=0;
	public static final int DELETE=1;
	public static final int SEARCH=2;
	
	int operation=-1;
	int key=-1;
	
	
	public TreeOperation(int operation, int key) {
		this.operation = operation;
		this.key = key;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	

}
