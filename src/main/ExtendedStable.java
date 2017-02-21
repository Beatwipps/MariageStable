package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ExtendedStable implements StableStrategie {

	
	
	
	public ExtendedStable(Map<String, TreeMap<Integer, List<String>>> hommemap, 
			Map<String, TreeMap<Integer, List<String>>> femmemap){
		
		
		
	}
	
	public void print(Map<String, TreeMap<Integer, List<String>>> hommeMap,
			Map<String, TreeMap<Integer, List<String>>> femmeMap){
		
		System.out.println("------- Preferences des Hommes --------");
		for (Entry<String, TreeMap<Integer, List<String>>> entry : hommeMap.entrySet()) {
			String key = entry.getKey();
			System.out.println(key);
			TreeMap<Integer, List<String>> myTree = entry.getValue();


			for (Entry<Integer, List<String>> entry1 : myTree.entrySet()) {
				System.out.println(entry1.getKey() + " => " + entry1.getValue());	              
			}
			System.out.println();
		}

		System.out.println("------- Preferences des Femmes --------");
		for (Entry<String, TreeMap<Integer, List<String>>> entry : femmeMap.entrySet()) {
			String key = entry.getKey();
			System.out.println(key);
			TreeMap<Integer, List<String>> myTree = entry.getValue();


			for (Entry<Integer, List<String>> entry1 : myTree.entrySet()) {
				System.out.println(entry1.getKey() + " => " + entry1.getValue());
			}
			System.out.println();
		}
	}


	
	@Override
	public void correspondance(Map<String, TreeMap<Integer, List<String>>> hommeMap,
			Map<String, TreeMap<Integer, List<String>>> femmeMap) {
		
		List<String> hommeLibre = new ArrayList<String>();
		List<String> femmeLibre = new ArrayList<String>();
		for (Entry<String, TreeMap<Integer, List<String>>> entry : hommeMap.entrySet()) {
			String keyF = entry.getKey();
			hommeLibre.add(keyF);
		}
		for (Entry<String, TreeMap<Integer, List<String>>> entry : femmeMap.entrySet()) {
			String keyH = entry.getKey();
			femmeLibre.add(keyH);
		}
		
		System.out.println(femmeLibre);
		System.out.println(hommeLibre);
		System.out.println(hommeMap.get("Sky").get(1));
//		while(!hommeLibre.isEmpty()){
//			
//		}
		
	}

	@Override
	public void verifieCorrespondance(Map<String, TreeMap<Integer, List<String>>> hommeMap,
			Map<String, TreeMap<Integer, List<String>>> femmeMap) {
		// TODO Auto-generated method stub
		
	}
}
