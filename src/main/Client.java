package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Client {

	public static void main(String[] args) {

		Map<String, ArrayList<List<String>>> preferenceDesFemmes;
		Map<String, ArrayList<List<String>>> preferenceDesHommes;  	
		List<String> hommes;
		List<String> femmes;
		
		LireFichier fichierFemmeBasic = 
				new LireFichier("./src/main/AffectationFemmeBasic.txt");
		LireFichier fichierHommeBasic = 
				new LireFichier("./src/main/AffectationHommeBasic.txt");
		preferenceDesFemmes = fichierFemmeBasic.getMap();	
		preferenceDesHommes = fichierHommeBasic.getMap();
		hommes = fichierHommeBasic.getList();
		femmes = fichierFemmeBasic.getList();
		StableStrategie B = new BasicStable();
		Instance I1 = new Instance(B, preferenceDesHommes, preferenceDesFemmes, hommes, femmes);
		I1.executeStableStrategie();
		
	
	}

}
