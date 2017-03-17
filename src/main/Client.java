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
		
		LireFichier fichierFemmeBasic = 
				new LireFichier("./src/main/AffectationFemmeBasic.txt");
		LireFichier fichierHommeBasic = 
				new LireFichier("./src/main/AffectationHommeBasic.txt");
		preferenceDes_B = fichierFemmeBasic.getMap();	
		preferenceDes_A = fichierHommeBasic.getMap();
		listeDes_A = fichierHommeBasic.getList();
		listeDes_B = fichierFemmeBasic.getList();
		StableStrategie B = new BasicStable();
		Instance I1 = new Instance(B, preferenceDes_A, preferenceDes_B, listeDes_A, listeDes_B);
		I1.executeStableStrategie();
		
	
	}

}
