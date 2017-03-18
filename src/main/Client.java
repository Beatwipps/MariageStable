package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Client {

	public static void main(String[] args) {

//		Map<String, ArrayList<List<String>>> preferenceDes_A;
//		Map<String, ArrayList<List<String>>> preferenceDes_B;  	
//		List<String> listeDes_A;
//		List<String> listeDes_B;
//		
//		LireFichier fichierBasic_B = new LireFichier("./src/main/AffectationBasic_B.txt");
//		LireFichier fichierBasic_A = new LireFichier("./src/main/AffectationBasic_A.txt");
//		LireFichier fichierExt_A = new LireFichier("./src/main/AffectationAvecIndifference_A.txt");
//		LireFichier fichierExt_B = new LireFichier("./src/main/AffectationAvecIndifference_B.txt");
//		preferenceDes_B = fichierExt_B.getMap();	
//		preferenceDes_A = fichierExt_A.getMap();
//		listeDes_A = fichierExt_A.getList();
//		listeDes_B = fichierExt_B.getList();
		StableStrategie E = new ExtendedStable();
		Instance I1 = new Instance(E,
				new LireFichier("./src/main/AffectationAvecIndifference_A.txt").getMap(),
				new LireFichier("./src/main/AffectationAvecIndifference_B.txt").getMap(),
				new LireFichier("./src/main/AffectationAvecIndifference_A.txt").getList(), 
				new LireFichier("./src/main/AffectationAvecIndifference_B.txt").getList()
				);
		I1.executeStableStrategie();
		
	
	}

}
