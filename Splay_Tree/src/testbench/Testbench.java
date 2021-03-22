package testbench;

import tree.SplayTree;
import tree.TreeNode;
import tree.TreeOperation;
import java.io.FileNotFoundException;

/**
 * 
 * @author jiin995
 * Permette di eseguire test su dei data set generati con con la TestbenchUtils.
 * 
 * 
 */

public class Testbench {
	
	
	TestbenchUtils testbenchUtils =new TestbenchUtils();
	
	public Testbench( ) {
	}
	
	public long startTest(String input_file) {
		long startTime=0,endTime=0,duration=0;

		try {
			//System.out.println("Test start");
			
			//Carico l'array dal file 
			TreeOperation operations []=testbenchUtils.readArrayFromFile(input_file);
			
			startTime=System.nanoTime();
			
			executeBatchOperations(operations);
			
			endTime=System.nanoTime();
			
			duration = ( endTime - startTime)/1000000 ;
		
			//System.out.println("\n"+endTime+"\n"+startTime);
			//System.out.println("\n"+algorithm.getName()+" duration :"+duration+"ms");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return duration;
	}
	
	public  void executeBatchOperations(TreeOperation operations[]) {
		SplayTree tree=new SplayTree();
		for (int i=0;i<operations.length;i++) {
			switch(operations[i].getOperation()) {
				case TreeOperation.INSERT:{
					tree.insert(new TreeNode(null,null,null,operations[i].getKey()));
					break;
				}
				case TreeOperation.DELETE:{
					tree.delete(tree.search(operations[i].getKey()));
					break;
				}
				case TreeOperation.SEARCH:{
					tree.search(operations[i].getKey());
					break;
				}
			}
		}
	}
	
}// end class
