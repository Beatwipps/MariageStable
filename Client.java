package main;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Client {

	public static void main(String[] args) {

		Map<String, TreeMap<Integer, List<String>>> femmeMap;
		Map<String, TreeMap<Integer, List<String>>> hommeMap;  	
		
		LireFichier fichierFemme = new LireFichier("/home/beatwipps/workspace/MariageStable/src/main/AffectationFemme.txt");
		femmeMap = fichierFemme.getMap();
		LireFichier fichierHomme = new LireFichier("/home/beatwipps/workspace/MariageStable/src/main/AffectationHomme.txt");
		hommeMap = fichierHomme.getMap();

		StableStrategie E = new ExtendedStable(hommeMap, femmeMap);
		Instance I1 = new Instance(E, hommeMap, femmeMap);
		I1.executeStableStrategie();
		
	
	}

}
