package testbench;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tree.TreeOperation;

/**
 * 
 * @author jiin995
 *	Insieme di classi e funzioni che aiutano nel realizzare testbench automatizzate
 *	
 *
 *	Il file scritto/letto ha la seguente struttura:
 *	dimensione
 *	TreeOperation == operation key
 *	TreeOperation[0]
 *	TreeOperation[1]
 *	 .
 *	 .
 *	TreeOperation[dimensione-1]
 */


public class TestbenchUtils {
	
	private String dataSet_dir="./DataSet";
	
	private boolean noMultipleKey=true;
	
	public TestbenchUtils() {}
	
	
	/**
	 * 
	 * @param input_file file di input se è null usa quello dell'ogetto
	 * @return Array contenente le operazioni da svolgere sull'albero
	 * @throws FileNotFoundException
	 */
	public TreeOperation[] readArrayFromFile(String input_file) throws FileNotFoundException{
		Scanner s = null;
			s = new Scanner(new File(dataSet_dir+"/"+input_file));
		 
		//leggo la dimensione del file e instanzio l'array
		TreeOperation[] array = new TreeOperation[s.nextInt()];
		
		//inizio l'operazione di lettura delle operazioni 
		for (int i = 0; i < array.length; i++) {
			array[i] =new TreeOperation(s.nextInt(),s.nextInt());	
		}
		
		//chiudo lo scanner 
		s.close();		
		return array;
	}

	
	/**
	 * 
	 * @param in			Operazioni da scrivere sul file
	 * @param output_file	file su cui scrivere l'array
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeArrayToFile(TreeOperation in[],String output_file) throws FileNotFoundException,IOException{
		FileWriter file=null;
			file= new FileWriter(dataSet_dir+"/"+output_file);
		
		BufferedWriter outputWriter = new BufferedWriter(file);

		//scrivo la dimensione dell'array sul file
		outputWriter.write(Integer.toString(in.length));
		outputWriter.newLine();
		
		//scrivo l'array sul file
		for (int i=0;i<in.length;i++) {
			outputWriter.write(Integer.toString(in[i].getOperation())+" "+Integer.toString(in[i].getKey()));
			outputWriter.newLine();
		}
		
		outputWriter.flush();
		outputWriter.close();
	}
	
	/**
	 * Crea un dataset di dimensione data e lo salva nel file system nella cartella DataSet_dimensione.txt
	 * @param dimensione	dimensione del dataset
	 * @throws Exception	dimensione negativa
	 */
	public void createRandomDatasetOnFile(int dimensione,int maxKey,boolean maxKeyName) throws Exception{

		TreeOperation operations [] = new TreeOperation [dimensione];
		//massimo valore di chiave che voglio assegnare
		int maxValue=maxKey;
		
		//vettore che mi indica lo stato di ciascuna chiave per poter evitare di inserire chiavi già inserite
		int available_keys[] = new int [maxValue];
		int n_operations[]=new int [3];
		
		int operation=-1;
		int key=-1;
		
		int i=0;
		//Create Array with length=dimensione and with random element from 0 to 2000
		if(dimensione >= 0) {
			while(i < dimensione) {
					//in[i]=(int)((Math.random()*dimensione)*(20*Math.random()));
				operation=(int)((Math.random()*3));
				key=(int)((Math.random()*maxValue));
				switch (operation) {
					case TreeOperation.INSERT:{
						/*	se noMultipleKey è true allora se l'elemento è stato già inserito e non rimosso allora non lo inserisco 
						* 	se è falso allora non effettuo il controllo
						*/
						if (this.noMultipleKey) {
							if (available_keys[key]==0) {
								operations[i]=new TreeOperation(operation,key);
								available_keys[key]++;
								i++;
								n_operations[operation]++;
							} 
						}else {
							operations[i]=new TreeOperation(operation,key);
							available_keys[key]++;
							i++;
							n_operations[operation]++;
						}
						break;
					}
					case TreeOperation.DELETE:{
						/*
						 * Se è già inserito un elemento allora rendo dinuovo disponibile quella chiave 
						 */
						if (available_keys[key]>0) {
							available_keys[key]--;
						}
						operations[i]=new TreeOperation(operation,key);
						i++;
						n_operations[operation]++;
						break;
					}
					case TreeOperation.SEARCH:{
						operations[i]=new TreeOperation(operation,key);
						i++;
						n_operations[operation]++;
						break;
					}
				}
				//Aggiorno il contatore dell'operazione inserita
			}
		}else{
				throw new Exception("Dimensione negativa");
		}
		String outputFile;
		if (maxKeyName)
			 outputFile=Integer.toString(maxKey);
		else
			outputFile=Integer.toString(dimensione);
		
		writeArrayToFile(operations, outputFile);
		System.out.println("Insert: "+n_operations[TreeOperation.INSERT]+"\nDelete: "+n_operations[TreeOperation.DELETE]+"\nSearch: "+n_operations[TreeOperation.SEARCH]);
		System.out.println("\nTotale: "+(n_operations[TreeOperation.INSERT]+n_operations[TreeOperation.DELETE]+n_operations[TreeOperation.SEARCH]));
	}
	
	public String [] listOfDataSet() {
		File folder = new File(dataSet_dir);
		File[] listOfFiles = folder.listFiles();
		String name[]= new String[listOfFiles.length];
		
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
			name[i]= listOfFiles[i].getName();
		    //System.out.println(i+" )" + listOfFiles[i].getName());
		  } 
		}
		return name;
	}
	
	public  Result[] testAllDataSet(String[] dataSets ) throws IOException{
		Testbench testbench=new Testbench();
		Result results[] = new Result [dataSets.length];
		
		for(int i=0;i<dataSets.length;i++) {
			System.out.println("Eseguo Test sul dataSet: "+dataSets[i]);
			long time=testbench.startTest(dataSets[i]);
			results[i]= new Result(dataSets[i],time);
			System.out.println("\t Tempo di esecuzione :"+time+"ms");

		}
		return results;
	}
	
	public void printListOfDataSet() {
		String DataSets[]=listOfDataSet();
		for (int i=0;i<DataSets.length;i++) {
				System.out.println("\t"+i+")\t"+DataSets[i]);
		}	}
	
/*	public void writeArrayToFile(int in[]) throws FileNotFoundException,IOException{
		this.writeArrayToFile(in, null);
	}
*/
	static public void printArray(int[] in) {
		for (int i=0;i<in.length;i++) {
			System.out.println(in[i]);
		}
	}
	
	static public void printArray1(int[] in) {
		for (int i=0;i<in.length;i++) {
			System.out.print(in[i]+"  ");
		}
	}
	
}
