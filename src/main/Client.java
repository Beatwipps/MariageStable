package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Client {

	public static void main(String[] args) {

		Map<String, ArrayList<List<String>>> preferenceDes_A;
		Map<String, ArrayList<List<String>>> preferenceDes_B;  	
		List<String> listeDes_A;
		List<String> listeDes_B;
		
		LireFichier fichierBasic_B = 
				new LireFichier("./src/main/AffectationBasic_B.txt");
		LireFichier fichierBasic_A = 
				new LireFichier("./src/main/AffectationBasic_A.txt");
		preferenceDes_B = fichierBasic_B.getMap();	
		preferenceDes_A = fichierBasic_A.getMap();
		listeDes_A = fichierBasic_A.getList();
		listeDes_B = fichierBasic_B.getList();
		StableStrategie B = new BasicStable();
		Instance I1 = new Instance(B, preferenceDes_A, preferenceDes_B, listeDes_A, listeDes_B);
		I1.executeStableStrategie();
		
	
	}

}
