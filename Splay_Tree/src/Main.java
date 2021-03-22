import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

import tree.SplayTree;
import tree.TreeNode;
import tree.Utils;
import testbench.Result;
import testbench.Testbench;
import testbench.TestbenchUtils;

@SuppressWarnings("unused")
public class Main {
	
	private static BufferedReader stdin = 
            new BufferedReader( new InputStreamReader( System.in ) );
	
	public static void main(String[] arg)  {
		/*
		SplayTree tree=new SplayTree();
		Scanner reader = new Scanner(System.in);  // Reading from System.in

		//tree.setRoot(new TreeNode(null,null,null,50));
	
		for (int i=0;i<10;i++) {
			int key=(int)(Math.random()*100);
			System.out.println("Insert key :"+key);
			tree.tree_insert(new TreeNode(null, null, null, key));
		}
		
		tree.insert(new TreeNode(null, null, null, 34));
		tree.insert(new TreeNode(null, null, null, 54));
		tree.insert(new TreeNode(null, null, null, 47));
		tree.insert(new TreeNode(null, null, null, 57));
		tree.insert(new TreeNode(null, null, null, 28));
		tree.insert(new TreeNode(null, null, null, 82));
		tree.insert(new TreeNode(null, null, null, 99));
		tree.insert(new TreeNode(null, null, null, 58));
		tree.insert(new TreeNode(null, null, null, 79));
		tree.insert(new TreeNode(null, null, null, 19));
	
		
		int key=0;
		while (key>=0) {
			System.out.println("Inserisci chiave :");
			key=reader.nextInt();
			tree.insert(new TreeNode(null, null, null, key));
			Utils.printTree(tree.getRoot());
		}
		
				reader.close();
		*/


		
	/*
		// Metto alcuni nodi nell'albero per fare dei test	
		TreeNode node = new TreeNode(tree.getRoot(),null,null,1);
			tree.getRoot().setLeft(node);
		node = new TreeNode(tree.getRoot(),null,null,2);
			tree.getRoot().setRight(node);
		node = new TreeNode(tree.getRoot().getLeft(),null,null,3);
			tree.getRoot().getLeft().setLeft(node);
		node = new TreeNode(tree.getRoot().getLeft(),null,null,4);
			tree.getRoot().getLeft().setRight(node);
			
		node = new TreeNode(tree.getRoot().getRight(),null,null,5);
			tree.getRoot().getRight().setLeft(node);
		node = new TreeNode(tree.getRoot().getRight(),null,null,6);
			tree.getRoot().getRight().setRight(node);
		
		tree.left_rotate(tree.getRoot());
		
		Utils.printTree(tree.getRoot());
		
		tree.right_rotate(tree.getRoot());
	
		Utils.printTree(tree.getRoot());

		System.out.println("Valore di chiave Minimo: "+tree.treeMinimum(tree.getRoot()).getKey());
		System.out.println("Successor della radice: "+tree.treeSuccessor(tree.getRoot().getRight()).getKey());
		TreeNode y=tree.deleteOld(tree.search(58));
		//TreeNode y=tree.search(58);
		
		System.out.println("Chiave Trovata:" +y.getKey());
		TestbenchUtils utils=new TestbenchUtils();
		try {
		
			utils.createRandomDatasetOnFile(2000);
		}catch(Exception e) {
			e.printStackTrace();
		}
			Utils.printTree(tree.getRoot());
*/
		application();
	
	}
	
	private static void application() {
		boolean repeat=true;
		TestbenchUtils testbenchUtils=new TestbenchUtils();
		Result risultati[] = null;
		
		do {
			try {
					System.out.println("1) Crea singolo dataSet");
					System.out.println("2) Crea serie di dataSet con numero di operazioni variabile;");
					System.out.println("3) Crea serie di dataSet con massimi valori di elementi variabile;");
					System.out.println("4) Visualizza lista dataSet ;");
					System.out.println("5) Esegui test su un dataSet;");
					System.out.println("6) Esegui test su tutti i dataSet ");
					System.out.println("7) Esegui prove ripetute su tutti i dataSet ");

					String input = stdin.readLine();
						int choose = Integer.parseInt(input);
					
					switch (choose) {
						case 1: {	System.out.println("\tInserisci dimensione ");
									input= stdin.readLine();
									int dimensione= Integer.parseInt(input);
									System.out.println("\tInserisci massimo valore di elementi ");
									input= stdin.readLine();
									int maxKey= Integer.parseInt(input);
									testbenchUtils.createRandomDatasetOnFile(dimensione,maxKey,false);
									System.out.println("\tDataset creato");
									break;
						}
						case 2: {	
									System.out.println("\tInserisci il numero minimo di operazioni ");
									int dimensioneMin= Integer.parseInt(stdin.readLine());
									System.out.println("\tInserisci il numero massimo di operazioni ");
									int dimensioneMax= Integer.parseInt(stdin.readLine());
									System.out.println("\tInserisci step tra le dimenensione dei dataSet");
									int step= Integer.parseInt(stdin.readLine());
									System.out.println("\tInserisci massimo valore di elementi");
									input= stdin.readLine();
									int maxKey= Integer.parseInt(input);
									if(dimensioneMax<step) {
										System.out.println("Dimensione non valida");
										break;
									}
									for(int i=dimensioneMin;i<=dimensioneMax;i=i+step) {
										testbenchUtils.createRandomDatasetOnFile(i,maxKey,false);
										System.out.println("\tDataset di dimensione "+i+" creato");
									}
								break;
						}
						case 3: {	
									System.out.println("\tInserisci il numero minimo di elementi");
									int dimensioneMin= Integer.parseInt(stdin.readLine());
									System.out.println("\tInserisci il numero massimo di elementi");
									int dimensioneMax= Integer.parseInt(stdin.readLine());
									System.out.println("\tInserisci step tra min e max");
									int step= Integer.parseInt(stdin.readLine());
									System.out.println("\tInserisci massimo numero di operazioni ");
									input= stdin.readLine();
									int maxOp= Integer.parseInt(input);
									if(dimensioneMax<step) {
										System.out.println("Dimensione non valida");
										break;
									}
									for(int i=dimensioneMin;i<=dimensioneMax;i=i+step) {
										testbenchUtils.createRandomDatasetOnFile(maxOp,i,true);
										System.out.println("\tDataset di dimensione "+i+" creato");
									}
								break;
						}
						case 4 :{
									testbenchUtils.printListOfDataSet();
									break;
						}
						case 5: {
									String dataSets[]=testbenchUtils.listOfDataSet();
									testbenchUtils.printListOfDataSet();
									System.out.print("Scelta : ");
									int indice=Integer.parseInt( stdin.readLine());
									Testbench testbench=new Testbench();
									float time =testbench.startTest(dataSets[indice]);		
									System.out.println("\n Tempo di esecuzione :"+time+"ms\n\n");

								break;
						}
						case 6 :{	
									FileWriter file= new FileWriter("Risultati.txt");
									BufferedWriter outputWriter = new BufferedWriter(file);
									String dataSets[]=testbenchUtils.listOfDataSet();

									Result results[] = testbenchUtils.testAllDataSet(dataSets);

									for(int i=0;i<dataSets.length;i++) {
										outputWriter.write(results[i].dataSetName+" "+Long.toString(results[i].time));
										outputWriter.newLine();
									}
									outputWriter.close();
									file.close();
									break;
						}
						case 7 :{	
									System.out.println("\tInserisci numero di prove");
									int nProve= Integer.parseInt(stdin.readLine());
									String dataSets[]=testbenchUtils.listOfDataSet();

									Result avg[]=new Result[dataSets.length];
									
									for(int i=0;i<dataSets.length;i++) {
										avg[i]=new Result(dataSets[i],0);
									}
					 
									for(int j=0;j<nProve;j++) {
										System.out.println("\tEseguo test numero: "+j);
										FileWriter file= new FileWriter("Risultati_"+j+".txt");
										BufferedWriter outputWriter = new BufferedWriter(file);
			
										Result results[] = testbenchUtils.testAllDataSet(dataSets);

										for(int i=0;i<dataSets.length;i++) {
											avg[i].time+=results[i].time;
											outputWriter.write(results[i].dataSetName+" "+Long.toString(results[i].time));
											outputWriter.newLine();
										}
										outputWriter.close();
										file.close();
									}
									
									// calcolo e salvo la media
									FileWriter file= new FileWriter("Avg.txt");
									BufferedWriter outputWriter = new BufferedWriter(file);
									for(int i=0;i<dataSets.length;i++) {
										avg[i].time=(avg[i].time)/nProve;
										outputWriter.write(avg[i].dataSetName+" "+Long.toString(avg[i].time));
										outputWriter.newLine();
									}
									outputWriter.close();
									file.close();
									break;
						}
						case 0:{	repeat=false;
									break;
						}
						default :{	System.out.println("Scelta errata");
									break;
						}
					}
			}catch (Exception e) {
					e.printStackTrace();
			}
		}while (repeat);
	}
	

}
